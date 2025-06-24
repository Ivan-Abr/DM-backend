package ru.kentagon.dm.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "recommendations")
class Recommendation(
    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layer_id", nullable = false)
    @JsonIgnore
    var layer: Layer,

    var value: Float,

    var annotation: String
)
