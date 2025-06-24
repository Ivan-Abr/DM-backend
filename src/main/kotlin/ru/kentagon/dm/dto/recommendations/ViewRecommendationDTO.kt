package ru.kentagon.dm.dto.recommendations

import ru.kentagon.dm.models.Recommendation
import java.util.UUID

data class  ViewRecommendationDTO(
    val id: UUID,
    val layerName: String,
    val value: Float,
    val annotation: String
) {
    constructor(recommendation: Recommendation): this(
        id = recommendation.id,
        layerName = recommendation.layer.name,
        value = recommendation.value,
        annotation = recommendation.annotation
    )
}
