package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.questions.CreateQuestionDTO
import ru.kentagon.dm.dto.questions.UpdateQuestionDTO
import ru.kentagon.dm.dto.questions.ViewQuestionDTO
import ru.kentagon.dm.models.Question
import ru.kentagon.dm.repositories.FactorRepository
import ru.kentagon.dm.repositories.LayerRepository
import ru.kentagon.dm.repositories.QuestionRepository
import java.util.UUID

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val layerRepository: LayerRepository,
    private val factorRepository: FactorRepository
) {
    fun getAllQuestions(): List<ViewQuestionDTO> = questionRepository.findAll().map { ViewQuestionDTO(it) }

    fun getQuestionById(id: UUID): ViewQuestionDTO = ViewQuestionDTO(questionRepository.findById(id).get())

    fun createQuestion(questionDTO: CreateQuestionDTO): Question {
        val layer = layerRepository.findById(questionDTO.layerId).get()
        val factor = factorRepository.findById(questionDTO.factorId).get()
        return questionRepository.save(
            Question(
            UUID.randomUUID(),
            layer,
            factor,
            questionDTO.name,
            questionDTO.annotation
            )
        )
    }

    fun updateQuestion(id: UUID, questionDTO: UpdateQuestionDTO): Question {
        val question = questionRepository.findById(id).get()
        questionDTO.layerId?.let { question.layer = layerRepository.findById(it).get() }
        questionDTO.factorId?.let { question.factor = factorRepository.findById(it).get() }
        questionDTO.name?.let { question.name = it }
        questionDTO.annotation?.let { question.annotation =  it}
        return questionRepository.save(question)
    }

    fun deleteQuestion(id: UUID){
        questionRepository.deleteById(id)
    }
}
