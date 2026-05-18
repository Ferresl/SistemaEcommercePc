package com.DuocucEcommerce.BenchmarkFps.Service;

import org.springframework.stereotype.Service;

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionRequestDTO;
import com.DuocucEcommerce.BenchmarkFps.Model.EstimacionFPS;
import com.DuocucEcommerce.BenchmarkFps.Repository.BenchmarkRepository;
import com.DuocucEcommerce.BenchmarkFps.Repository.EstimacionFPSRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FpsService {
    private final BenchmarkRepository benchmarkRepository;
    private final EstimacionFPSRepository estimacionRepository;

    public EstimacionFPSResponseDTO estimar(EstimacionRequestDTO dto) {
        Double fps = benchmarkRepository.findByCpuIdAndGpuIdAndVideojuegoIdAndResolucionId(dto.getCpuId(), dto.getGpuId(), dto.getVideojuegoId(), dto.getResolucionId())
                .map(b -> b.getFpsPromedio())
                .orElseGet(() -> estimacionSimple(dto.getCpuId(), dto.getGpuId()));
        EstimacionFPS estimacion = estimacionRepository.save(EstimacionFPS.builder().configuracionId(dto.getConfiguracionId()).fpsEstimado(fps).build());
        return EstimacionFPSResponseDTO.builder().id(estimacion.getId()).configuracionId(estimacion.getConfiguracionId()).fpsEstimado(estimacion.getFpsEstimado()).build();
    }

    private Double estimacionSimple(Integer cpuId, Integer gpuId) {
        return benchmarkRepository.findByCpuIdAndGpuId(cpuId, gpuId).stream().mapToDouble(b -> b.getFpsPromedio()).average()
                .orElse(benchmarkRepository.findByGpuId(gpuId).stream().mapToDouble(b -> b.getFpsPromedio()).average().orElse(60.0));
    }
}
