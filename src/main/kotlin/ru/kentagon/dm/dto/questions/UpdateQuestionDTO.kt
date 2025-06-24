package ru.kentagon.dm.dto.questions

import java.util.*

data class UpdateQuestionDTO(
    val layerId: UUID?,
    val factorId: UUID?,
    val name: String?,
    val annotation: String?
)
