package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "questions")
class Question(
    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layer_id", nullable = false)
    var layer: Layer,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factor_id", nullable = false)
    var factor: Factor,

    var name: String,

    var annotation: String
)