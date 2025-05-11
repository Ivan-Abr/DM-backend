package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Organization
import ru.kentagon.dm.models.User
import java.util.UUID

@Repository
interface OrganizationRepository : JpaRepository<Organization, UUID> {
    @Query("SELECT o FROM Organization o WHERE o.expert = ?1")
    fun getOrganizationsByUser(user: User): List<Organization>
}