package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.factors.CreateFactorDTO
import ru.kentagon.dm.dto.factors.UpdateFactorDTO
import ru.kentagon.dm.models.Factor
import ru.kentagon.dm.repositories.FactorRepository
import java.util.UUID

@Service
class FactorService(private val factorRepository: FactorRepository) {
    fun getAllFactors(): List<Factor> = factorRepository.findAll()

    fun getFactorByID(id: UUID): Factor = factorRepository.findById(id).get()

    fun createFactor(factorDTO: CreateFactorDTO): Factor =
        factorRepository.save(Factor(name = factorDTO.name, shortname = factorDTO.shortname))

    fun updateFactor(id: UUID, factorDTO: UpdateFactorDTO): Factor {
        val factor = factorRepository.findById(id).get()
        factorDTO.name?.let { factor.name = it }
        factorDTO.shortname?.let { factor.shortname = it }
        return factorRepository.save(factor)
    }

    fun deleteFactor(id: UUID) {
        factorRepository.deleteById(id)
    }
}
