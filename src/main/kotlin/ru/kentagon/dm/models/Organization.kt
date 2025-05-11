package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "organizations")
class Organization(
    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "expert_id", nullable = false)
    var expert: User,

    var name: String,

    var annotation: String,

    var contacts: String
)
