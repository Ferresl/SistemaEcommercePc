package com.DuocucEcommerce.BenchmarkFps.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionRequestDTO;
import com.DuocucEcommerce.BenchmarkFps.Model.Benchmark;
import com.DuocucEcommerce.BenchmarkFps.Model.EstimacionFPS;
import com.DuocucEcommerce.BenchmarkFps.Repository.BenchmarkRepository;
import com.DuocucEcommerce.BenchmarkFps.Repository.EstimacionFPSRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class FpsServiceTest {

    @Mock
    private BenchmarkRepository benchmarkRepository;

    @Mock
    private EstimacionFPSRepository estimacionRepository;

    @InjectMocks
    private FpsService service;

    private EstimacionRequestDTO request;
    private Benchmark benchmark;
    private EstimacionFPS estimacion;

    @BeforeEach
    void setUp() {
        request = new EstimacionRequestDTO(1, 2, 3, 4, 5);
        benchmark = Benchmark.builder().id(1).cpuId(2).gpuId(3).videojuegoId(4).resolucionId(5).fpsPromedio(95.5).build();
        estimacion = EstimacionFPS.builder().id(1).configuracionId(1).fpsEstimado(95.5).build();
    }

    @Test
    void estimar_conBenchmarkExacto_retornaFpsPromedio() {
        // ARRANGE
        when(benchmarkRepository.findByCpuIdAndGpuIdAndVideojuegoIdAndResolucionId(2, 3, 4, 5)).thenReturn(Optional.of(benchmark));
        when(estimacionRepository.save(any(EstimacionFPS.class))).thenReturn(estimacion);

        // ACT
        EstimacionFPSResponseDTO resultado = service.estimar(request);

        // ASSERT
        assertEquals(95.5, resultado.getFpsEstimado());
        assertEquals(1, resultado.getConfiguracionId());
    }
}