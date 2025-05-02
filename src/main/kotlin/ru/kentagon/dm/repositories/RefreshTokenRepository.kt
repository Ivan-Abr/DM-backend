package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kentagon.dm.models.RefreshToken
import ru.kentagon.dm.models.User
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun deleteByUser(user: User)
}
