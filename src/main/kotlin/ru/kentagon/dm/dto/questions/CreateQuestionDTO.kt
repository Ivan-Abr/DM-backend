package ru.kentagon.dm.dto.questions

import java.util.UUID

data class CreateQuestionDTO(
    val layerId: UUID,
    val factorId: UUID,
    val name: String,
    val annotation: String
)
