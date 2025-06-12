package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.recommendations.CreateRecommendationDTO
import ru.kentagon.dm.dto.recommendations.UpdateRecommendationDTO
import ru.kentagon.dm.dto.recommendations.ViewRecommendationDTO
import ru.kentagon.dm.models.Recommendation
import ru.kentagon.dm.repositories.FactorRepository
import ru.kentagon.dm.repositories.LayerRepository
import ru.kentagon.dm.repositories.RecommendationRepository
import java.util.*

@Service
class RecommendationService(
    private val recommendationRepository: RecommendationRepository,
    private val layerRepository: LayerRepository,
    private val factorRepository: FactorRepository
) {
    fun getAllRecommendations(): List<ViewRecommendationDTO> = recommendationRepository.findAll().map { ViewRecommendationDTO(it) }

    fun getRecommendationById(id: UUID): ViewRecommendationDTO = ViewRecommendationDTO(recommendationRepository.findById(id).get())

    fun getRecommendationByValueAndID(value: Float, id: UUID): ViewRecommendationDTO =
        ViewRecommendationDTO(recommendationRepository.getByLayerAndValue(value, id).get())

    fun createRecommendation(recommendationDTO: CreateRecommendationDTO): Recommendation {
        val layer = layerRepository.findById(recommendationDTO.layerId).get()
        return recommendationRepository.save(
            Recommendation(
                UUID.randomUUID(),
                layer,
                recommendationDTO.value,
                recommendationDTO.annotation
            )
        )
    }

    fun updateRecommendation(id: UUID, recommendationDTO: UpdateRecommendationDTO): Recommendation {
        val recommendation = recommendationRepository.findById(id).get()
        recommendationDTO.layerId?.let { recommendation.layer = layerRepository.findById(it).get() }
        recommendationDTO.value?.let { recommendation.value = it }
        recommendationDTO.annotation?.let { recommendation.annotation =  it}
        return recommendationRepository.save(recommendation)
    }

    fun deleteRecommendation(id: UUID){
        recommendationRepository.deleteById(id)
    }
}