package ru.kentagon.dm.dto.questions

import ru.kentagon.dm.models.Question
import java.util.*

data class ViewQuestionDTO(
    val name: String,
    val layerName: String,
    val factorShortname: String,
    val annotation: String
) {
    constructor(question: Question): this(
        name = question.name,
        layerName = question.layer.name,
        factorShortname = question.factor.shortname,
        annotation = question.annotation
    )
}
