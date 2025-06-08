package ru.kentagon.dm.dto.recommendations

import java.util.*

data class UpdateRecommendationDTO(
    val layerId: UUID?,
    val value: Float?,
    val annotation: String?
)
