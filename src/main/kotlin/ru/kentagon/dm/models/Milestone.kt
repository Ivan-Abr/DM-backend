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
    var id: UUID = UUID.randomUUID(),

    @Column(name = "date_from")
    var dateFrom: LocalDate,

    @Column(name = "date_to")
    var dateTo: LocalDate,

    var year: Int
)
