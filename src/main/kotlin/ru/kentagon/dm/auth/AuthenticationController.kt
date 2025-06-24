package ru.kentagon.dm.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kentagon.dm.services.RefreshTokenService
import ru.kentagon.dm.auth.dto.LoginRequestDTO
import ru.kentagon.dm.auth.dto.RefreshRequestDTO
import ru.kentagon.dm.auth.dto.RegisterRequestDTO
import ru.kentagon.dm.auth.dto.ResponseDTO


@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val refreshTokenService: RefreshTokenService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequestDTO
    ): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(authenticationService.register(request))
    }

    @PostMapping("/login")
    fun authenticate(
        @RequestBody request: LoginRequestDTO
    ): ResponseEntity<ResponseDTO?> {
        return ResponseEntity.ok(authenticationService.authenticate(request))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequestDTO): ResponseEntity<ResponseDTO> {
       return authenticationService.refresh(request)
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
        authenticationService.logout(token)
        return ResponseEntity.ok("User logged out successfully")
    }
}