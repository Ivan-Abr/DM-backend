package ru.kentagon.dm.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "milestones")
class Milestone(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "date_from")
    val dateFrom: LocalDate,

    @Column(name = "date_to")
    val dateTo: LocalDate,

    val year: Int
)
