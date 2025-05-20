package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "marks")
class Mark(
    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    var question: Question,

    var annotation: String,

    var value: Int
)
