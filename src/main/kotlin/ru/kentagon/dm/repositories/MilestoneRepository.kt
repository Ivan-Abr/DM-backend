package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Milestone
import java.util.UUID

@Repository
interface MilestoneRepository : JpaRepository<Milestone, UUID>
