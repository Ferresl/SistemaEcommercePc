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

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.EstimacionFPS;
import com.DuocucEcommerce.BenchmarkFps.Repository.EstimacionFPSRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class EstimacionFPSServiceTest {

    @Mock
    private EstimacionFPSRepository repository; // repositorio simulado

    @InjectMocks
    private EstimacionFPSService service; // servicio real con el repo simulado inyectado

    private EstimacionFPS ejemplo;
    private EstimacionFPSCreateDTO createDTO;
    private EstimacionFPSUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new EstimacionFPS();
        ejemplo.setId(1);
        ejemplo.setConfiguracionId(1);
        ejemplo.setFpsEstimado(88.0);

        createDTO = EstimacionFPSCreateDTO.builder()
                .configuracionId(1)
                .fpsEstimado(88.0)
                .build();

        updateDTO = EstimacionFPSUpdateDTO.builder()
                .configuracionId(1)
                .fpsEstimado(88.0)
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE
        List<EstimacionFPS> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<EstimacionFPSResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT
        EstimacionFPSResponseDTO resultado = service.buscarPorId(1);

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

        assertEquals("EstimacionFPS no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaRegistroGuardado() {
        // ARRANGE
        when(repository.save(any(EstimacionFPS.class))).thenReturn(ejemplo);

        // ACT
        EstimacionFPSResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any(EstimacionFPS.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        EstimacionFPSResponseDTO resultado = service.actualizar(1, updateDTO);

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