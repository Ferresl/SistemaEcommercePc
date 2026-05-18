package com.DuocucEcommerce.Comparador.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionUpdateDTO;
import com.DuocucEcommerce.Comparador.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Comparador.Model.ItemComparacion;
import com.DuocucEcommerce.Comparador.Repository.ItemComparacionRepository;

@Service
@RequiredArgsConstructor
public class ItemComparacionService {
    private static final Logger log = LoggerFactory.getLogger(ItemComparacionService.class);
    private final ItemComparacionRepository repository;

    public List<ItemComparacionResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public ItemComparacionResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public ItemComparacionResponseDTO crear(ItemComparacionCreateDTO dto) {
        log.info("Creando item comparacion");
        ItemComparacion itemComparacion = new ItemComparacion();
        copiarDatos(dto, itemComparacion);
        return toResponse(repository.save(itemComparacion));
    }
    public ItemComparacionResponseDTO actualizar(Integer id, ItemComparacionUpdateDTO dto) {
        log.info("Actualizando item comparacion con id {}", id);
        ItemComparacion itemComparacion = obtenerEntidad(id);
        copiarDatos(dto, itemComparacion);
        return toResponse(repository.save(itemComparacion));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private ItemComparacion obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItemComparacion no encontrado con id " + id));
    }
    private void copiarDatos(ItemComparacionCreateDTO dto, ItemComparacion itemComparacion) {
        itemComparacion.setComparacionId(dto.getComparacionId());
        itemComparacion.setProductoId(dto.getProductoId());
    }
    private void copiarDatos(ItemComparacionUpdateDTO dto, ItemComparacion itemComparacion) {
        itemComparacion.setComparacionId(dto.getComparacionId());
        itemComparacion.setProductoId(dto.getProductoId());
    }
    private ItemComparacionResponseDTO toResponse(ItemComparacion itemComparacion) {
        return ItemComparacionResponseDTO.builder()
                .id(itemComparacion.getId())
                .comparacionId(itemComparacion.getComparacionId())
                .productoId(itemComparacion.getProductoId())
                .build();
    }
}