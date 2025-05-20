package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kentagon.dm.dto.marks.CreateMarkDTO
import ru.kentagon.dm.dto.marks.UpdateMarkDTO
import ru.kentagon.dm.dto.marks.ViewMarkDTO
import ru.kentagon.dm.models.Mark
import ru.kentagon.dm.services.MarkService
import java.util.UUID

@RestController
@RequestMapping("api/mark")
class MarkController(private val markService: MarkService) {
    @GetMapping
    fun getAllMarks(): List<ViewMarkDTO> = markService.getAllMarks().map { ViewMarkDTO(it) }

    @GetMapping("/{id}")
    fun getMarkById(@PathVariable id: UUID): ViewMarkDTO = ViewMarkDTO(markService.getMarkById(id))

    @PostMapping
    fun createMark(@RequestBody markDTO: CreateMarkDTO): Mark = markService.createMark(markDTO)

    @PatchMapping("/{id}")
    fun updateMark(@PathVariable id: UUID, @RequestBody markDTO: UpdateMarkDTO): Mark =
        markService.updateMark(id, markDTO)

    @DeleteMapping("/{id}")
    fun deleteMark(@PathVariable id: UUID) = markService.deleteMark(id)
}
