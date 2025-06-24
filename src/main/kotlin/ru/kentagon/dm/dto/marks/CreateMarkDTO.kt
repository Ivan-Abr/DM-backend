package ru.kentagon.dm.dto.marks

import java.util.UUID

data class CreateMarkDTO(
    val questionId: UUID,
    val annotation: String,
    val value: Int
)