package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.kentagon.dm.models.User
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    fun findByName(name: String?): Optional<User>

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    fun findByEmail(email: String?): Optional<User>

    fun existsByName(name: String): Boolean
    fun existsByEmail(email: String): Boolean
}
