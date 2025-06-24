package ru.kentagon.dm.dto.user

import ru.kentagon.dm.models.User
import java.util.UUID

data class ViewUserDTO(
    val id: UUID,
    val name: String,
    val email: String,
    val role: String
) {
    constructor(user: User): this(
        id = user.id,
        name = user.name,
        email = user.email,
        role = user.role.toString()
    )
}
