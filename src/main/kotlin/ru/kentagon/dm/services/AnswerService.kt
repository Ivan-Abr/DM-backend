package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.answers.CreateAnswerDTO
import ru.kentagon.dm.dto.answers.UpdateAnswerDTO
import ru.kentagon.dm.models.Answer
import ru.kentagon.dm.repositories.AnswerRepository
import ru.kentagon.dm.repositories.MarkRepository
import ru.kentagon.dm.repositories.MilestoneRepository
import ru.kentagon.dm.repositories.OrganizationRepository
import java.util.*

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val organizationRepository: OrganizationRepository,
    private val milestoneRepository: MilestoneRepository,
    private val markRepository: MarkRepository
    ) {
    fun getAllAnswers(): List<Answer> = answerRepository.findAll()

    fun getAnswerById(id: UUID): Answer = answerRepository.findById(id).get()

    fun createAnswer(answerDTO: CreateAnswerDTO): Answer =
        answerRepository.save(
            Answer(
                UUID.randomUUID(),
                organization = organizationRepository.findById(answerDTO.organizationId).get(),
                mark = markRepository.findById(answerDTO.markId).get(),
                milestone = milestoneRepository.findById(answerDTO.milestoneId).get()
            )
        )

    fun updateAnswer(id: UUID, answerDTO: UpdateAnswerDTO): Answer {
        val answer = answerRepository.findById(id).get()
        answerDTO.organizationId?.let { answer.organization = organizationRepository.findById(it).get() }
        answerDTO.markId?.let { answer.mark = markRepository.findById(it).get() }
        answerDTO.milestoneId?.let { answer.milestone = milestoneRepository.findById(it).get() }
        return answerRepository.save(answer)
    }

    fun deleteAnswer(id: UUID) = answerRepository.deleteById(id)

    fun getAllDataByLayerOrg(layerId: UUID, orgId: UUID): Any{
        return answerRepository.getAllByLayerOrg(layerId, orgId)
    }

    fun getAllDataByFactorOrg(factorId: UUID, orgId: UUID): Any{
        return answerRepository.getAllByFactorOrg(factorId, orgId)
    }

    fun getAllDataByOrg(orgId: UUID): List<Any>{
        return answerRepository.getAllByOrgId(orgId)
    }
}