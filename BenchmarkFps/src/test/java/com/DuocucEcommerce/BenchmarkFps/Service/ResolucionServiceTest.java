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

import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Resolucion;
import com.DuocucEcommerce.BenchmarkFps.Repository.ResolucionRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class ResolucionServiceTest {

    @Mock
    private ResolucionRepository repository; // repositorio simulado

    @InjectMocks
    private ResolucionService service; // servicio real con el repo simulado inyectado

    private Resolucion ejemplo;
    private ResolucionCreateDTO createDTO;
    private ResolucionUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new Resolucion();
        ejemplo.setId(1);
        ejemplo.setNombre("1920x1080");

        createDTO = ResolucionCreateDTO.builder()
                .nombre("1920x1080")
                .build();

        updateDTO = ResolucionUpdateDTO.builder()
                .nombre("1920x1080")
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE
        List<Resolucion> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<ResolucionResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT
        ResolucionResponseDTO resultado = service.buscarPorId(1);

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

        assertEquals("Resolucion no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaRegistroGuardado() {
        // ARRANGE
        when(repository.save(any(Resolucion.class))).thenReturn(ejemplo);

        // ACT
        ResolucionResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any(Resolucion.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        ResolucionResponseDTO resultado = service.actualizar(1, updateDTO);

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