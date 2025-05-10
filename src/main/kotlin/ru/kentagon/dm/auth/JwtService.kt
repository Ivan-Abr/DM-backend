package ru.kentagon.dm.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.kentagon.dm.repositories.UserRepository
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey
import kotlin.collections.HashMap

@Service
class JwtService(private val userRepository: UserRepository) {

    @Value("\${dm.jwt.secret-key}")
    private lateinit var secretKey: String

    @Value("\${dm.jwt.access-duration}")
    private var expirationDate: Long = 1000 * 60 * 60 * 24

    fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    private fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T? {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    fun generateToken(userDetails: UserDetails): String {
        val authorities = userDetails.authorities
        val roles = authorities.map { it.authority }
        return generateToken(mapOf("roles" to roles), userDetails)
    }

    fun generateToken(
        extraClaims: Map<String?, Any?>?,
        userDetails: UserDetails
    ): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expirationDate))
            .signWith<SecretKey>(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username: String = extractUsername(token)!!
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token)!!.before(Date())
    }

    private fun extractExpiration(token: String): Date? {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractRoles(token: String): List<String> {
        val claims = extractAllClaims(token)
        return claims.get("roles", List::class.java) as List<String>
    }
}
