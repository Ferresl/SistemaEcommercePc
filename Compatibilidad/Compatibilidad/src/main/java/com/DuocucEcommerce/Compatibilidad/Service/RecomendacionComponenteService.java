package com.DuocucEcommerce.Compatibilidad.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.RecomendacionComponente;
import com.DuocucEcommerce.Compatibilidad.Repository.RecomendacionComponenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecomendacionComponenteService {
    private static final Logger log = LoggerFactory.getLogger(RecomendacionComponenteService.class);
    
    private final RecomendacionComponenteRepository repository;

    public List<RecomendacionComponenteResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public RecomendacionComponenteResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public RecomendacionComponenteResponseDTO crear(RecomendacionComponenteCreateDTO dto) {
        log.info("Creando recomendacion componente");
        RecomendacionComponente recomendacionComponente = new RecomendacionComponente();
        copiarDatos(dto, recomendacionComponente);
        return toResponse(repository.save(recomendacionComponente));
    }
    
    public RecomendacionComponenteResponseDTO actualizar(Integer id, RecomendacionComponenteUpdateDTO dto) {
        log.info("Actualizando recomendacion componente con id {}", id);
        RecomendacionComponente recomendacionComponente = obtenerEntidad(id);
        copiarDatos(dto, recomendacionComponente);
        return toResponse(repository.save(recomendacionComponente));
    }
    public void eliminar(Integer id) { 
        repository.delete(obtenerEntidad(id)); 
    }
    
    private RecomendacionComponente obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RecomendacionComponente no encontrado con id " + id));
    }
    
    private void copiarDatos(RecomendacionComponenteCreateDTO dto, RecomendacionComponente recomendacionComponente) {
        recomendacionComponente.setConflictoId(dto.getConflictoId());
        recomendacionComponente.setProductoRecomendadoId(dto.getProductoRecomendadoId());
        recomendacionComponente.setMotivo(dto.getMotivo());
    }
    
    private void copiarDatos(RecomendacionComponenteUpdateDTO dto, RecomendacionComponente recomendacionComponente) {
        recomendacionComponente.setConflictoId(dto.getConflictoId());
        recomendacionComponente.setProductoRecomendadoId(dto.getProductoRecomendadoId());
        recomendacionComponente.setMotivo(dto.getMotivo());
    }
    
    
    private RecomendacionComponenteResponseDTO toResponse(RecomendacionComponente recomendacionComponente) {
        return RecomendacionComponenteResponseDTO.builder()
                .id(recomendacionComponente.getId())
                .conflictoId(recomendacionComponente.getConflictoId())
                .productoRecomendadoId(recomendacionComponente.getProductoRecomendadoId())
                .motivo(recomendacionComponente.getMotivo())
                .build();
    }
}