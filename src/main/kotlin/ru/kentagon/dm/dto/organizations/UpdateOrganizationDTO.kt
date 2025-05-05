package ru.kentagon.dm.dto.organizations

import java.util.*

class UpdateOrganizationDTO(
    val expertId: UUID?,
    val name: String?,
    val annotation: String?,
    val contacts: String?
)