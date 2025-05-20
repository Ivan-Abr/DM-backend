package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kentagon.dm.dto.questions.CreateQuestionDTO
import ru.kentagon.dm.dto.questions.UpdateQuestionDTO
import ru.kentagon.dm.dto.questions.ViewQuestionDTO
import ru.kentagon.dm.models.Layer
import ru.kentagon.dm.models.Question
import ru.kentagon.dm.services.QuestionService
import java.util.UUID

@RestController
@RequestMapping("api/question")
class QuestionController(private val questionService: QuestionService) {
    @GetMapping
    fun getAllQuestions(): List<ViewQuestionDTO> = questionService.getAllQuestions()

    @GetMapping("/{id}")
    fun getQuestionById(@PathVariable id: UUID): ViewQuestionDTO =
        questionService.getQuestionById(id)

    @PostMapping
    fun createQuestion(@RequestBody questionDTO: CreateQuestionDTO): Question =
        questionService.createQuestion(questionDTO)

    @PatchMapping("/{id}")
    fun updateQuestion(@PathVariable id: UUID,@RequestBody questionDTO: UpdateQuestionDTO): Question =
        questionService.updateQuestion(id, questionDTO)

    @DeleteMapping("/{id}")
    fun deleteQuestion(@PathVariable id: UUID) {
        questionService.deleteQuestion(id)
    }
}
