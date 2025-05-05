package ru.kentagon.dm.dto.marks

import java.util.*

data class UpdateMarkDTO(
    val questionId: UUID?,
    val annotation: String?,
    val value: Int?
)
