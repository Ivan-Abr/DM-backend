package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.marks.CreateMarkDTO
import ru.kentagon.dm.dto.marks.UpdateMarkDTO
import ru.kentagon.dm.models.Mark
import ru.kentagon.dm.repositories.MarkRepository
import ru.kentagon.dm.repositories.QuestionRepository
import java.util.UUID

@Service
class MarkService(
    private val markRepository: MarkRepository,
    private val questionRepository: QuestionRepository
) {
    fun getAllMarks(): List<Mark> = markRepository.findAll()

    fun getMarkById(id: UUID): Mark = markRepository.findById(id).get()

    fun createMark(markDTO: CreateMarkDTO): Mark {
        val question = questionRepository.findById(markDTO.questionId).get()
        return Mark(
            UUID.randomUUID(),
            question,
            markDTO.annotation,
            markDTO.value
        )
    }

    fun updateMark(id: UUID, markDTO: UpdateMarkDTO): Mark {
        val mark = markRepository.findById(id).get()
        markDTO.questionId?.let { mark.question = questionRepository.findById(it).get() }
        markDTO.value?.let { mark.value = it }
        markDTO.annotation?.let { mark.annotation = it }
        return markRepository.save(mark)
    }

    fun deleteMark(id: UUID) = markRepository.deleteById(id)
}
