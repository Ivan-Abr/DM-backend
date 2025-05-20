package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kentagon.dm.dto.factors.CreateFactorDTO
import ru.kentagon.dm.dto.factors.UpdateFactorDTO
import ru.kentagon.dm.models.Factor
import ru.kentagon.dm.services.FactorService
import java.util.UUID

@RestController
@RequestMapping("api/factor")
class FactorController(private val factorService: FactorService) {
    @GetMapping
    fun getAllFactors(): List<Factor> = factorService.getAllFactors()

    @GetMapping("/{id}")
    fun getFactorById(@PathVariable id: UUID): Factor = factorService.getFactorByID(id)

    @PostMapping
    fun createFactor(@RequestBody factorDTO: CreateFactorDTO): Factor = factorService.createFactor(factorDTO)

    @PatchMapping("/{id}")
    fun updateFactor(@PathVariable id: UUID, @RequestBody factorDTO: UpdateFactorDTO): Factor =
        factorService.updateFactor(id, factorDTO)

    @DeleteMapping("/{id}")
    fun deleteFactor(@PathVariable id: UUID) = factorService.deleteFactor(id)
}
