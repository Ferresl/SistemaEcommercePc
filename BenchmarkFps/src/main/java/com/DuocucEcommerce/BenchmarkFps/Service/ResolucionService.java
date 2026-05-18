package com.DuocucEcommerce.BenchmarkFps.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Resolucion;
import com.DuocucEcommerce.BenchmarkFps.Repository.ResolucionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResolucionService {
    private static final Logger log = LoggerFactory.getLogger(ResolucionService.class);
    private final ResolucionRepository repository;

    public List<ResolucionResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public ResolucionResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public ResolucionResponseDTO crear(ResolucionCreateDTO dto) {
        log.info("Creando resolucion");
        Resolucion resolucion = new Resolucion();
        copiarDatos(dto, resolucion);
        return toResponse(repository.save(resolucion));
    }
    public ResolucionResponseDTO actualizar(Integer id, ResolucionUpdateDTO dto) {
        log.info("Actualizando resolucion con id {}", id);
        Resolucion resolucion = obtenerEntidad(id);
        copiarDatos(dto, resolucion);
        return toResponse(repository.save(resolucion));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private Resolucion obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resolucion no encontrado con id " + id));
    }
    private void copiarDatos(ResolucionCreateDTO dto, Resolucion resolucion) {
        resolucion.setNombre(dto.getNombre());
    }
    private void copiarDatos(ResolucionUpdateDTO dto, Resolucion resolucion) {
        resolucion.setNombre(dto.getNombre());
    }
    private ResolucionResponseDTO toResponse(Resolucion resolucion) {
        return ResolucionResponseDTO.builder()
                .id(resolucion.getId())
                .nombre(resolucion.getNombre())
                .build();
    }
}
