package ru.kentagon.dm.dto.recommendations

import org.springframework.beans.factory.annotation.Value
import ru.kentagon.dm.models.Recommendation

data class ViewRecommendationDTO(
    val layerName: String,
    val value: Float,
    val annotation: String
) {
    constructor(recommendation: Recommendation): this(
        layerName = recommendation.layer.name,
        value = recommendation.value,
        annotation = recommendation.annotation
    )
}
