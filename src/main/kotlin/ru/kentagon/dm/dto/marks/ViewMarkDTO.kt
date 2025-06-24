package ru.kentagon.dm.dto.marks

import ru.kentagon.dm.models.Mark
import java.util.UUID

data class ViewMarkDTO(
    val id: UUID,
    val questionId: UUID,
    val annotation: String,
    val value: Int
) {
    constructor(mark: Mark): this(
        id = mark.id,
        questionId = mark.question.id,
        annotation = mark.annotation,
        value = mark.value
    )
}
