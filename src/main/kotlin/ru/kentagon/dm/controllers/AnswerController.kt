package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.*
import ru.kentagon.dm.dto.answers.CreateAnswerDTO
import ru.kentagon.dm.dto.answers.UpdateAnswerDTO
import ru.kentagon.dm.models.Answer
import ru.kentagon.dm.services.AnswerService
import java.util.*

@RestController
@RequestMapping("api/answer")
class AnswerController(private val answerService: AnswerService) {
    @GetMapping
    fun getAllAnswers(): List<Answer> = answerService.getAllAnswers()

    @GetMapping("/{id}")
    fun getAnswerById(@PathVariable id: UUID): Answer =
        answerService.getAnswerById(id)

    @PostMapping
    fun createAnswer(@RequestBody answerDTO: CreateAnswerDTO): Answer =
        answerService.createAnswer(answerDTO)

    @PatchMapping("/{id}")
    fun updateAnswer(@PathVariable id: UUID, @RequestBody answerDTO: UpdateAnswerDTO): Answer =
        answerService.updateAnswer(id, answerDTO)

    @DeleteMapping("/{id}")
    fun deleteAnswer(@PathVariable id: UUID) {
        answerService.deleteAnswer(id)
    }

    @GetMapping(path = ["all/layer/{layerId}/org/{orgId}"])
    fun getAllDataByLayerOrg(@PathVariable("layerId") layerId:Long,
                             @PathVariable("orgId") orgId:Long): Any{
        return answerService.getAllDataByLayerOrg(layerId,orgId)
    }

    @GetMapping(path = ["all/org/{orgId}"])
    fun getAllDataByOrg(@PathVariable("orgId") orgId:Long): List<Any>{
        return answerService.getAllDataByOrg(orgId)
    }

    @GetMapping(path = ["all/factor/{factorId}/org/{orgId}"])
    fun getAllDataByFactorOrg(@PathVariable("factorId") factorId:Long,
                              @PathVariable("orgId") orgId:Long): Any{
        return answerService.getAllDataByFactorOrg(factorId,orgId)
    }
}
