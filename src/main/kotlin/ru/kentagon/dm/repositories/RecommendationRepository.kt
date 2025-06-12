package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Recommendation
import java.util.Optional
import java.util.UUID

@Repository
interface RecommendationRepository : JpaRepository<Recommendation, UUID> {
    @Query("SELECT r FROM Recommendation r " +
        "WHERE r.value = (SELECT MIN(r.value) FROM Recommendation r " +
        "WHERE r.value > ?1)" +
        "AND r.layer.id= ?2")
    fun getByLayerAndValue(value: Float, layerId: UUID): Optional<Recommendation>
}
