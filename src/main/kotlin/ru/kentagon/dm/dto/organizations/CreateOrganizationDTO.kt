package ru.kentagon.dm.dto.organizations

import java.util.UUID

data class CreateOrganizationDTO(
    val expertId: UUID,
    val name: String,
    val annotation: String,
    val contacts: String
)