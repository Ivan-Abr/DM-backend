package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kentagon.dm.dto.user.UpdateUserDTO
import ru.kentagon.dm.dto.user.ViewUserDTO
import ru.kentagon.dm.models.User
import ru.kentagon.dm.services.UserService
import java.util.UUID

@RestController
@RequestMapping("api/user")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getAllUsers(): List<ViewUserDTO> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(id: UUID) = userService.getUserById(id)

    @PatchMapping("/{id}")
    fun updateUser(@PathVariable id: UUID, @RequestBody userDTO: UpdateUserDTO): User =
        userService.updateUser(id, userDTO)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID) = userService.deleteUser(id)
}
