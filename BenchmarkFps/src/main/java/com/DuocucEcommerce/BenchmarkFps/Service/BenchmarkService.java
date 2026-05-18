package com.DuocucEcommerce.BenchmarkFps.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Benchmark;
import com.DuocucEcommerce.BenchmarkFps.Repository.BenchmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BenchmarkService {
    private static final Logger log = LoggerFactory.getLogger(BenchmarkService.class);
    private final BenchmarkRepository repository;

    public List<BenchmarkResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public BenchmarkResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public BenchmarkResponseDTO crear(BenchmarkCreateDTO dto) {
        log.info("Creando benchmark");
        Benchmark benchmark = new Benchmark();
        copiarDatos(dto, benchmark);
        return toResponse(repository.save(benchmark));
    }
    public BenchmarkResponseDTO actualizar(Integer id, BenchmarkUpdateDTO dto) {
        log.info("Actualizando benchmark con id {}", id);
        Benchmark benchmark = obtenerEntidad(id);
        copiarDatos(dto, benchmark);
        return toResponse(repository.save(benchmark));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private Benchmark obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Benchmark no encontrado con id " + id));
    }
    private void copiarDatos(BenchmarkCreateDTO dto, Benchmark benchmark) {
        benchmark.setCpuId(dto.getCpuId());
        benchmark.setGpuId(dto.getGpuId());
        benchmark.setVideojuegoId(dto.getVideojuegoId());
        benchmark.setResolucionId(dto.getResolucionId());
        benchmark.setFpsPromedio(dto.getFpsPromedio());
    }
    private void copiarDatos(BenchmarkUpdateDTO dto, Benchmark benchmark) {
        benchmark.setCpuId(dto.getCpuId());
        benchmark.setGpuId(dto.getGpuId());
        benchmark.setVideojuegoId(dto.getVideojuegoId());
        benchmark.setResolucionId(dto.getResolucionId());
        benchmark.setFpsPromedio(dto.getFpsPromedio());
    }
    private BenchmarkResponseDTO toResponse(Benchmark benchmark) {
        return BenchmarkResponseDTO.builder()
                .id(benchmark.getId())
                .cpuId(benchmark.getCpuId())
                .gpuId(benchmark.getGpuId())
                .videojuegoId(benchmark.getVideojuegoId())
                .resolucionId(benchmark.getResolucionId())
                .fpsPromedio(benchmark.getFpsPromedio())
                .build();
    }
}