package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.organizations.CreateOrganizationDTO
import ru.kentagon.dm.dto.organizations.UpdateOrganizationDTO
import ru.kentagon.dm.models.Organization
import ru.kentagon.dm.repositories.OrganizationRepository
import ru.kentagon.dm.repositories.UserRepository
import java.util.*

@Service
class OrganizationService(
    private val organizationRepository: OrganizationRepository,
    private val userRepository: UserRepository
) {
    fun getAllOrganizations(): List<Organization> = organizationRepository.findAll()

    fun getOrganizationById(id: UUID): Organization = organizationRepository.findById(id).get()

    fun createOrganization(organizationDTO: CreateOrganizationDTO): Organization =
        organizationRepository.save(
            Organization(
                UUID.randomUUID(),
                userRepository.findById(organizationDTO.expertId).get(),
                organizationDTO.name,
                organizationDTO.annotation,
                organizationDTO.contacts
            )
        )

    fun updateOrganization(id: UUID, organizationDTO: UpdateOrganizationDTO): Organization {
        val organization = organizationRepository.findById(id).get()
        organizationDTO.expertId?.let { organization.expert = userRepository.findById(it).get() }
        organizationDTO.name?.let { organization.name = it }
        organizationDTO.annotation?.let { organization.annotation = it }
        organizationDTO.contacts?.let { organization.contacts = it }
        return organizationRepository.save(organization)
    }

    fun deleteOrganization(id: UUID) = organizationRepository.deleteById(id)
}