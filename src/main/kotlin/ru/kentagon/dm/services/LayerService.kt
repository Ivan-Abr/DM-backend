package ru.kentagon.dm.services

import org.springframework.stereotype.Service
import ru.kentagon.dm.dto.layers.CreateLayerDTO
import ru.kentagon.dm.dto.layers.UpdateLayerDTO
import ru.kentagon.dm.models.Layer
import ru.kentagon.dm.repositories.LayerRepository
import java.util.UUID

@Service
class LayerService(private val layerRepository: LayerRepository) {
    fun getAllLayers(): List<Layer> = layerRepository.findAll()

    fun getLayerById(id: UUID): Layer = layerRepository.findById(id).get()

    fun createLayer(createLayerDTO: CreateLayerDTO): Layer =
        layerRepository.save(Layer(name = createLayerDTO.name))

    fun updateLayer(id: UUID, updateLayerDTO: UpdateLayerDTO) : Layer {
        val layer = layerRepository.findById(id).get()
        updateLayerDTO.name?.let { layer.name = it }
        return layerRepository.save(layer)
    }

    fun deleteLayer(id: UUID) {
        layerRepository.deleteById(id)
    }
}
