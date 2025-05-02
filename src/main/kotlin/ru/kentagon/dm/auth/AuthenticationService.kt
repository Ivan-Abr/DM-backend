package ru.kentagon.dm.auth

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.kentagon.dm.models.Role
import ru.kentagon.dm.models.User
import ru.kentagon.dm.repositories.UserRepository
import ru.kentagon.dm.services.RefreshTokenService
import ru.kentagon.dm.auth.dto.LoginRequestDTO
import ru.kentagon.dm.auth.dto.RefreshRequestDTO
import ru.kentagon.dm.auth.dto.RegisterRequestDTO
import ru.kentagon.dm.auth.dto.ResponseDTO
import ru.kentagon.dm.config.JwtService
import java.util.*

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val manager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenService: RefreshTokenService
) {

    fun register(request: RegisterRequestDTO): ResponseDTO {

        val usernameRegex = Regex("^[a-zA-Zа-яА-Я0-9_-]{3,20}$")
        val emailRegex = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")

        require(usernameRegex.matches(request.username)) {
            "Имя пользователя должно содержать от 3 до 20 символов и включать буквы, цифры, _ или -"
        }
        require(emailRegex.matches(request.email)) { "Некорректный формат email" }
        require(passwordRegex.matches(request.password)) {
            "Пароль должен содержать минимум 8 символов, хотя бы одну букву и одну цифру"
        }

        if (userRepository.existsByName(request.username)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Имя пользователя уже занято")
        }
        if (userRepository.existsByEmail(request.email)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Email уже зарегистрирован")
        }

        val user = User(
            id = UUID.randomUUID(),
            name = request.username,
            email = request.email,
            userPassword = passwordEncoder.encode(request.password),
            role = Role.USER,
        )
        userRepository.save(user)
        val jwtToken = jwtService.generateToken(user)
        val refreshToken = refreshTokenService.createRefreshToken(user)
        return ResponseDTO(
            message = "Регистрация успешна",
            token = jwtToken,
            refreshToken = refreshToken.id.toString()
        )
    }

    fun authenticate(request: LoginRequestDTO): ResponseDTO {

        try {
            val user = userRepository.findByName(request.username)
                .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверное имя пользователя или пароль") }
            manager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
            val jwtToken = jwtService.generateToken(user)
            val refreshToken = refreshTokenService.createRefreshToken(user)
            return ResponseDTO(
                message = "Вход выполнен успешно",
                token = jwtToken,
                refreshToken = refreshToken.id.toString()

            )
        } catch (ex: BadCredentialsException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверное имя пользователя или пароль")
        } catch (ex: AuthenticationException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверное имя пользователя или пароль")
        }
    }

    fun refresh(request: RefreshRequestDTO) : ResponseEntity<ResponseDTO> {
        val refreshToken = refreshTokenService.verifyRefreshToken(request.refreshTokenId)
        val user = refreshToken.user
        val newAccessToken = jwtService.generateToken(user)
        return ResponseEntity.ok(
            ResponseDTO(
            token = newAccessToken,
            refreshToken = refreshToken.id.toString(),
            message = "Refresh Success"
        )
        )
    }

    fun logout(token: String) {
        val username = jwtService.extractUsername(token.substring(7))
        val user = userRepository.findByName(username).orElseThrow { RuntimeException("User not found") }
        refreshTokenService.deleteByUser(user)
    }

}
