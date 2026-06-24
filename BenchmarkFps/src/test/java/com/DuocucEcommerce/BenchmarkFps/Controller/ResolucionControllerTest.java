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

import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Service.ResolucionService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class ResolucionControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private ResolucionService service; // servicio falso

    @InjectMocks
    private ResolucionController controller;

    private ResolucionResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = ResolucionResponseDTO.builder().id(1)
                .nombre("1920x1080")
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE
        List<ResolucionResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/resoluciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/resoluciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Resolucion no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/resoluciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(ResolucionCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/resoluciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nombre":"1920x1080"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(ResolucionUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/resoluciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nombre":"1920x1080"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/resoluciones/1"))
                .andExpect(status().isNoContent());
        verify(service, times(1)).eliminar(1);
    }
}