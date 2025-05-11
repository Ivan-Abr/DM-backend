package ru.kentagon.dm.dto.user

import java.util.UUID

data class UpdateUserDTO(
    val name: String?,
    val email: String?,
    val password: String?
)
