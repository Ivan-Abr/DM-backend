package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Organization
import java.util.UUID

@Repository
interface OrgainzationRepository : JpaRepository<Organization, UUID>