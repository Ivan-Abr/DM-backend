package ru.kentagon.dm.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "layers")
class Layer(
    @Id
    val id: UUID = UUID.randomUUID(),

    val name: String
)
