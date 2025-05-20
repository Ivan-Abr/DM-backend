package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.*
import ru.kentagon.dm.dto.milestones.CreateMilestoneDTO
import ru.kentagon.dm.dto.milestones.UpdateMilestoneDTO
import ru.kentagon.dm.models.Milestone
import ru.kentagon.dm.services.MilestoneService
import java.util.*


@RestController
@RequestMapping("api/milestone")
class MilestoneController(private val milestoneService: MilestoneService) {
    @GetMapping
    fun getAllMilestones(): List<Milestone> = milestoneService.getAllMilestones()

    @GetMapping("/{id}")
    fun getMilestoneById(@PathVariable id: UUID): Milestone =
        milestoneService.getMilestoneById(id)

    @PostMapping
    fun createMilestone(@RequestBody milestoneDTO: CreateMilestoneDTO): Milestone =
        milestoneService.createMilestone(milestoneDTO)

    @PatchMapping("/{id}")
    fun updateMilestone(@PathVariable id: UUID,@RequestBody milestoneDTO: UpdateMilestoneDTO): Milestone =
        milestoneService.updateMilestone(id, milestoneDTO)

    @DeleteMapping("/{id}")
    fun deleteMilestone(@PathVariable id: UUID) {
        milestoneService.deleteMilestone(id)
    }
}
