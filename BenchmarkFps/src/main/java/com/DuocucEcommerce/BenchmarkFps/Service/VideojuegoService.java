package com.DuocucEcommerce.BenchmarkFps.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Videojuego;
import com.DuocucEcommerce.BenchmarkFps.Repository.VideojuegoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideojuegoService {
    private static final Logger log = LoggerFactory.getLogger(VideojuegoService.class);
    private final VideojuegoRepository repository;

    public List<VideojuegoResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public VideojuegoResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public VideojuegoResponseDTO crear(VideojuegoCreateDTO dto) {
        log.info("Creando videojuego");
        Videojuego videojuego = new Videojuego();
        copiarDatos(dto, videojuego);
        return toResponse(repository.save(videojuego));
    }
    public VideojuegoResponseDTO actualizar(Integer id, VideojuegoUpdateDTO dto) {
        log.info("Actualizando videojuego con id {}", id);
        Videojuego videojuego = obtenerEntidad(id);
        copiarDatos(dto, videojuego);
        return toResponse(repository.save(videojuego));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private Videojuego obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado con id " + id));
    }
    private void copiarDatos(VideojuegoCreateDTO dto, Videojuego videojuego) {
        videojuego.setNombre(dto.getNombre());
    }
    private void copiarDatos(VideojuegoUpdateDTO dto, Videojuego videojuego) {
        videojuego.setNombre(dto.getNombre());
    }
    private VideojuegoResponseDTO toResponse(Videojuego videojuego) {
        return VideojuegoResponseDTO.builder()
                .id(videojuego.getId())
                .nombre(videojuego.getNombre())
                .build();
    }
}
