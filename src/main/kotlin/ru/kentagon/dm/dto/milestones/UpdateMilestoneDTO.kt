package ru.kentagon.dm.dto.milestones

import java.time.LocalDate

data class UpdateMilestoneDTO(
    val dateFrom: LocalDate?,
    val dateTo: LocalDate?,
    val year: Int?
)
