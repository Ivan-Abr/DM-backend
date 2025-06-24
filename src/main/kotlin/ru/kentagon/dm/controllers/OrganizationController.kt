package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.*
import ru.kentagon.dm.dto.organizations.CreateOrganizationDTO
import ru.kentagon.dm.dto.organizations.UpdateOrganizationDTO
import ru.kentagon.dm.dto.organizations.ViewOrganizationDTO
import ru.kentagon.dm.models.Organization
import ru.kentagon.dm.services.OrganizationService
import java.util.*

@RestController
@RequestMapping("api/organization")
class OrganizationController(private val organizationService: OrganizationService) {
    @GetMapping
    fun getAllOrganizations(): List<ViewOrganizationDTO> = organizationService.getAllOrganizations()

    @GetMapping("/user/{id}")
    fun getOrganizationsByUserId(@PathVariable id: UUID): List<ViewOrganizationDTO> =
        organizationService.getOrganizationsByUser(id)

    @GetMapping("/{id}")
    fun getOrganizationById(@PathVariable id: UUID): ViewOrganizationDTO =
        organizationService.getOrganizationById(id)

    @PostMapping
    fun createOrganization(@RequestBody organizationDTO: CreateOrganizationDTO): Organization =
        organizationService.createOrganization(organizationDTO)


    @PatchMapping("/{id}")
    fun updateOrganization(@PathVariable id: UUID, @RequestBody organizationDTO: UpdateOrganizationDTO): Organization =
        organizationService.updateOrganization(id, organizationDTO)

    @DeleteMapping("/{id}")
    fun deleteOrganization(@PathVariable id: UUID) {
        organizationService.deleteOrganization(id)
    }
}
