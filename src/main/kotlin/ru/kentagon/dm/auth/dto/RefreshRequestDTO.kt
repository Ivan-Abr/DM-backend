package ru.kentagon.dm.auth.dto

import java.util.UUID

data class RefreshRequestDTO(
    val refreshTokenId: UUID
)
