package ru.kentagon.dm.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "factors")
class Factor(
    @Id
    var id: UUID = UUID.randomUUID(),

    var name: String,

    var shortname: String
)
