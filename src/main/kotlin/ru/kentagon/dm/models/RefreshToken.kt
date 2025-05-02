package ru.kentagon.dm.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "refresh_tokens")
class RefreshToken(
    @Id
    var id: UUID,

    @Column(name = "expiry_date")
    var expiryDate: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
)
