package ru.kentagon.dm.dto.organizations

import ru.kentagon.dm.models.Organization
import java.util.*

data class ViewOrganizationDTO(
    val id: UUID,
    val expertId: UUID,
    val name: String,
    val annotation: String,
    val contacts: String
) {
    constructor(organization: Organization): this(
        id = organization.id,
        expertId = organization.expert.id,
        name = organization.name,
        annotation = organization.annotation,
        contacts = organization.contacts
    )
}
