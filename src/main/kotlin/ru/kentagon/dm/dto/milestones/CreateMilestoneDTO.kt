package ru.kentagon.dm.dto.milestones

import java.time.LocalDate

data class CreateMilestoneDTO(
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val year: Int
)
