package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "answers")
class Answer(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    val organization: Organization,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_id", nullable =  false)
    val mark: Mark,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    val milestone: Milestone
)
