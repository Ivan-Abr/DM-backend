package ru.kentagon.dm.dto.answers

import java.util.UUID

data class CreateAnswerDTO(
    val organizationId: UUID,
    val markId: UUID,
    val milestoneId: UUID
)
