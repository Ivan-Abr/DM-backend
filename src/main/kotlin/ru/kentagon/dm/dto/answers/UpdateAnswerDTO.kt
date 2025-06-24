package ru.kentagon.dm.dto.answers

import java.util.*

data class UpdateAnswerDTO(
    val organizationId: UUID?,
    val markId: UUID?,
    val milestoneId: UUID?
)
