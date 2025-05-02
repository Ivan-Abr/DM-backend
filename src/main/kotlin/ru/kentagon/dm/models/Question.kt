package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "questions")
class Question(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layer_id", nullable = false)
    val layer: Layer,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factor_id", nullable = false)
    val factor: Factor,

    val name: String,

    val annotation: String
)