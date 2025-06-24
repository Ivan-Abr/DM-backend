package ru.kentagon.dm.dto.questions

import ru.kentagon.dm.models.Question
import java.util.*

data class ViewQuestionDTO(
    val id: UUID,
    val name: String,
    val layerName: String,
    val factorShortname: String,
    val annotation: String
) {
    constructor(question: Question): this(
        id = question.id,
        name = question.name,
        layerName = question.layer.name,
        factorShortname = question.factor.shortname,
        annotation = question.annotation
    )
}
