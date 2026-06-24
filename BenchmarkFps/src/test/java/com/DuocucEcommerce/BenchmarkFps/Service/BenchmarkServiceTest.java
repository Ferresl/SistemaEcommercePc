package com.DuocucEcommerce.BenchmarkFps.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Benchmark;
import com.DuocucEcommerce.BenchmarkFps.Repository.BenchmarkRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class BenchmarkServiceTest {

    @Mock
    private BenchmarkRepository repository; // repositorio simulado

    @InjectMocks
    private BenchmarkService service; // servicio real con el repo simulado inyectado

    private Benchmark ejemplo;
    private BenchmarkCreateDTO createDTO;
    private BenchmarkUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new Benchmark();
        ejemplo.setId(1);
        ejemplo.setCpuId(1);
        ejemplo.setGpuId(2);
        ejemplo.setVideojuegoId(3);
        ejemplo.setResolucionId(4);
        ejemplo.setFpsPromedio(95.5);

        createDTO = BenchmarkCreateDTO.builder()
                .cpuId(1)
                .gpuId(2)
                .videojuegoId(3)
                .resolucionId(4)
                .fpsPromedio(95.5)
                .build();

        updateDTO = BenchmarkUpdateDTO.builder()
                .cpuId(1)
                .gpuId(2)
                .videojuegoId(3)
                .resolucionId(4)
                .fpsPromedio(95.5)
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE
        List<Benchmark> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<BenchmarkResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT
        BenchmarkResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Benchmark no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaRegistroGuardado() {
        // ARRANGE
        when(repository.save(any(Benchmark.class))).thenReturn(ejemplo);

        // ACT
        BenchmarkResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any(Benchmark.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        BenchmarkResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(ejemplo);
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(ejemplo);
    }
}