package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Mark
import java.util.UUID

@Repository
interface MarkRepository : JpaRepository<Mark, UUID> {
    @Query("SELECT m from Mark m WHERE m.question.id = ?1")
    fun getMarksByQuestion(questionId: UUID): List<Mark>
}
