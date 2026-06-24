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

import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Model.Videojuego;
import com.DuocucEcommerce.BenchmarkFps.Repository.VideojuegoRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class VideojuegoServiceTest {

    @Mock
    private VideojuegoRepository repository; // repositorio simulado

    @InjectMocks
    private VideojuegoService service; // servicio real con el repo simulado inyectado

    private Videojuego ejemplo;
    private VideojuegoCreateDTO createDTO;
    private VideojuegoUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new Videojuego();
        ejemplo.setId(1);
        ejemplo.setNombre("Cyberpunk 2077");

        createDTO = VideojuegoCreateDTO.builder()
                .nombre("Cyberpunk 2077")
                .build();

        updateDTO = VideojuegoUpdateDTO.builder()
                .nombre("Cyberpunk 2077")
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE
        List<Videojuego> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<VideojuegoResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT
        VideojuegoResponseDTO resultado = service.buscarPorId(1);

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

        assertEquals("Videojuego no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaRegistroGuardado() {
        // ARRANGE
        when(repository.save(any(Videojuego.class))).thenReturn(ejemplo);

        // ACT
        VideojuegoResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any(Videojuego.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        VideojuegoResponseDTO resultado = service.actualizar(1, updateDTO);

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