package ru.kentagon.dm.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.user.UpdateUserDTO
import ru.kentagon.dm.dto.user.ViewUserDTO
import ru.kentagon.dm.models.User
import ru.kentagon.dm.repositories.RefreshTokenRepository
import ru.kentagon.dm.repositories.UserRepository
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun getAllUsers() = userRepository.findAll().map { ViewUserDTO(it) }

    fun getUserById(id: UUID): ViewUserDTO =
        ViewUserDTO(userRepository.findById(id).get())

    fun getUserByName(name: String): ViewUserDTO =
        ViewUserDTO(userRepository.findByName(name).get())

    fun updateUser(id: UUID, userDTO: UpdateUserDTO): User {
        val user = userRepository.findById(id).get()
        userDTO.name?.let { user.name = it }
        userDTO.email?.let { user.email = it}
        userDTO.password?.let { user.userPassword = passwordEncoder.encode(it) }

        return userRepository.save(user)
    }

    fun deleteUser(id: UUID) = userRepository.deleteById(id)
}
