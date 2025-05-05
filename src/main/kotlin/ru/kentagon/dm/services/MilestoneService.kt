package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.milestones.CreateMilestoneDTO
import ru.kentagon.dm.dto.milestones.UpdateMilestoneDTO
import ru.kentagon.dm.models.Milestone
import ru.kentagon.dm.repositories.MilestoneRepository
import java.util.UUID

@Service
class MilestoneService(private val milestoneRepository: MilestoneRepository) {
    fun getAllMilestones(): List<Milestone> = milestoneRepository.findAll()

    fun getMilestoneById(id: UUID): Milestone = milestoneRepository.findById(id).get()

    fun createMilestone(milestoneDTO: CreateMilestoneDTO): Milestone =
        milestoneRepository.save(
            Milestone(
                UUID.randomUUID(),
                milestoneDTO.dateFrom,
                milestoneDTO.dateTo,
                milestoneDTO.year
            )
        )

    fun updateMilestone(id: UUID, milestoneDTO: UpdateMilestoneDTO): Milestone {
        val milestone = milestoneRepository.findById(id).get()
        milestoneDTO.dateFrom?.let { milestone.dateFrom =it }
        milestoneDTO.dateTo?.let { milestone.dateTo = it }
        milestoneDTO.year?.let { milestone.year = it }
        return milestoneRepository.save(milestone)
    }

    fun deleteMilestone(id: UUID) = milestoneRepository.deleteById(id)
}
