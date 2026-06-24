package com.DuocucEcommerce.BenchmarkFps.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Service.VideojuegoService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class VideojuegoControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private VideojuegoService service; // servicio falso

    @InjectMocks
    private VideojuegoController controller;

    private VideojuegoResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = VideojuegoResponseDTO.builder().id(1)
                .nombre("Cyberpunk 2077")
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE
        List<VideojuegoResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/videojuegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/videojuegos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Videojuego no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/videojuegos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(VideojuegoCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nombre":"Cyberpunk 2077"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(VideojuegoUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/videojuegos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nombre":"Cyberpunk 2077"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/videojuegos/1"))
                .andExpect(status().isNoContent());
        verify(service, times(1)).eliminar(1);
    }
}