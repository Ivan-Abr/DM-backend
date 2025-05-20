package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.kentagon.dm.models.RefreshToken
import ru.kentagon.dm.models.User
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun deleteByUser(user: User)
    @Query("SELECT r FROM RefreshToken r WHERE r.user = ?1")
    fun getByUser(user: User): RefreshToken
}
