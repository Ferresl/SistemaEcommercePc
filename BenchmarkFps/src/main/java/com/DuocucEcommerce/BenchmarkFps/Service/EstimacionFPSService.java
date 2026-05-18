package com.DuocucEcommerce.BenchmarkFps.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.EstimacionFPS;
import com.DuocucEcommerce.BenchmarkFps.Repository.EstimacionFPSRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstimacionFPSService {
    private static final Logger log = LoggerFactory.getLogger(EstimacionFPSService.class);
    private final EstimacionFPSRepository repository;

    public List<EstimacionFPSResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public EstimacionFPSResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public EstimacionFPSResponseDTO crear(EstimacionFPSCreateDTO dto) {
        log.info("Creando estimacion fps");
        EstimacionFPS estimacionFPS = new EstimacionFPS();
        copiarDatos(dto, estimacionFPS);
        return toResponse(repository.save(estimacionFPS));
    }
    public EstimacionFPSResponseDTO actualizar(Integer id, EstimacionFPSUpdateDTO dto) {
        log.info("Actualizando estimacion fps con id {}", id);
        EstimacionFPS estimacionFPS = obtenerEntidad(id);
        copiarDatos(dto, estimacionFPS);
        return toResponse(repository.save(estimacionFPS));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private EstimacionFPS obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EstimacionFPS no encontrado con id " + id));
    }
    private void copiarDatos(EstimacionFPSCreateDTO dto, EstimacionFPS estimacionFPS) {
        estimacionFPS.setConfiguracionId(dto.getConfiguracionId());
        estimacionFPS.setFpsEstimado(dto.getFpsEstimado());
    }
    private void copiarDatos(EstimacionFPSUpdateDTO dto, EstimacionFPS estimacionFPS) {
        estimacionFPS.setConfiguracionId(dto.getConfiguracionId());
        estimacionFPS.setFpsEstimado(dto.getFpsEstimado());
    }
    private EstimacionFPSResponseDTO toResponse(EstimacionFPS estimacionFPS) {
        return EstimacionFPSResponseDTO.builder()
                .id(estimacionFPS.getId())
                .configuracionId(estimacionFPS.getConfiguracionId())
                .fpsEstimado(estimacionFPS.getFpsEstimado())
                .build();
    }
}
