package com.DuocucEcommerce.Compatibilidad.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.ConflictoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ConflictoCompatibilidadRepository;

@Service
@RequiredArgsConstructor
public class ConflictoCompatibilidadService {
    private static final Logger log = LoggerFactory.getLogger(ConflictoCompatibilidadService.class);
    
    private final ConflictoCompatibilidadRepository repository;

    public List<ConflictoCompatibilidadResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    
    public ConflictoCompatibilidadResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    
    public ConflictoCompatibilidadResponseDTO crear(ConflictoCompatibilidadCreateDTO dto) {
        log.info("Creando conflicto compatibilidad");
        ConflictoCompatibilidad conflictoCompatibilidad = new ConflictoCompatibilidad();
        copiarDatos(dto, conflictoCompatibilidad);
        return toResponse(repository.save(conflictoCompatibilidad));
    }
    
    public ConflictoCompatibilidadResponseDTO actualizar(Integer id, ConflictoCompatibilidadUpdateDTO dto) {
        log.info("Actualizando conflicto compatibilidad con id {}", id);
        ConflictoCompatibilidad conflictoCompatibilidad = obtenerEntidad(id);
        copiarDatos(dto, conflictoCompatibilidad);
        return toResponse(repository.save(conflictoCompatibilidad));
    }
    
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    
    private ConflictoCompatibilidad obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConflictoCompatibilidad no encontrado con id " + id));
    }
    private void copiarDatos(ConflictoCompatibilidadCreateDTO dto, ConflictoCompatibilidad conflictoCompatibilidad) {
        conflictoCompatibilidad.setResultadoId(dto.getResultadoId());
        conflictoCompatibilidad.setProductoAId(dto.getProductoAId());
        conflictoCompatibilidad.setProductoBId(dto.getProductoBId());
        conflictoCompatibilidad.setMotivo(dto.getMotivo());
    }
    private void copiarDatos(ConflictoCompatibilidadUpdateDTO dto, ConflictoCompatibilidad conflictoCompatibilidad) {
        conflictoCompatibilidad.setResultadoId(dto.getResultadoId());
        conflictoCompatibilidad.setProductoAId(dto.getProductoAId());
        conflictoCompatibilidad.setProductoBId(dto.getProductoBId());
        conflictoCompatibilidad.setMotivo(dto.getMotivo());
    }
    private ConflictoCompatibilidadResponseDTO toResponse(ConflictoCompatibilidad conflictoCompatibilidad) {
        return ConflictoCompatibilidadResponseDTO.builder()
                .id(conflictoCompatibilidad.getId())
                .resultadoId(conflictoCompatibilidad.getResultadoId())
                .productoAId(conflictoCompatibilidad.getProductoAId())
                .productoBId(conflictoCompatibilidad.getProductoBId())
                .motivo(conflictoCompatibilidad.getMotivo())
                .build();
    }
}