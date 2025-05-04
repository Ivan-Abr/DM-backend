package ru.kentagon.dm.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kentagon.dm.dto.layers.CreateLayerDTO
import ru.kentagon.dm.dto.layers.UpdateLayerDTO
import ru.kentagon.dm.models.Layer
import ru.kentagon.dm.services.LayerService
import java.util.UUID

@RestController
@RequestMapping("api/layer")
class LayerController(private val layerService: LayerService) {
    @GetMapping
    fun getAllLayers(): List<Layer> = layerService.getAllLayers()

    @GetMapping("/{id}")
    fun getLayerById(@PathVariable id: UUID): Layer = layerService.getLayerById(id)

    @PostMapping
    fun createLayer(@RequestBody layerDTO: CreateLayerDTO): Layer = layerService.createLayer(layerDTO)

    @PatchMapping("/{id}")
    fun updateLayer(@PathVariable id: UUID, @RequestBody layerDTO: UpdateLayerDTO): Layer =
        layerService.updateLayer(id, layerDTO)

    @DeleteMapping("/{id}")
    fun deleteLayer(@PathVariable id: UUID) {
        layerService.deleteLayer(id)
    }
}
