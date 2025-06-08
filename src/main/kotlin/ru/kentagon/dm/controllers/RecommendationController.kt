package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.*
import ru.kentagon.dm.dto.recommendations.CreateRecommendationDTO
import ru.kentagon.dm.dto.recommendations.UpdateRecommendationDTO
import ru.kentagon.dm.dto.recommendations.ViewRecommendationDTO
import ru.kentagon.dm.models.Recommendation
import ru.kentagon.dm.services.RecommendationService
import java.util.*

@RestController
@RequestMapping("api/recommendation")
class RecommendationController(private val recommendationService: RecommendationService) {
    @GetMapping
    fun getAllRecommendations(): List<ViewRecommendationDTO> = recommendationService.getAllRecommendations()

    @GetMapping("/{id}")
    fun getRecommendationById(@PathVariable id: UUID): ViewRecommendationDTO =
        recommendationService.getRecommendationById(id)

    @PostMapping
    fun createRecommendation(@RequestBody recommendationDTO: CreateRecommendationDTO): Recommendation =
        recommendationService.createRecommendation(recommendationDTO)

    @PatchMapping("/{id}")
    fun updateRecommendation(@PathVariable id: UUID, @RequestBody recommendationDTO: UpdateRecommendationDTO): Recommendation =
        recommendationService.updateRecommendation(id, recommendationDTO)

    @DeleteMapping("/{id}")
    fun deleteRecommendation(@PathVariable id: UUID) {
        recommendationService.deleteRecommendation(id)
    }
}
