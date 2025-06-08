package ru.kentagon.dm.dto.recommendations

import java.util.UUID

data class CreateRecommendationDTO(
    val layerId: UUID,
    val value: Float,
    val annotation: String
)
