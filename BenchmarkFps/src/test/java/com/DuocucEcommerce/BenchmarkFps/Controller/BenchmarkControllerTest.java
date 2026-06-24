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

import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.BenchmarkFps.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.BenchmarkFps.Service.BenchmarkService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class BenchmarkControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private BenchmarkService service; // servicio falso

    @InjectMocks
    private BenchmarkController controller;

    private BenchmarkResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = BenchmarkResponseDTO.builder().id(1)
                .cpuId(1)
                .gpuId(2)
                .videojuegoId(3)
                .resolucionId(4)
                .fpsPromedio(95.5)
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE
        List<BenchmarkResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/benchmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/benchmarks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Benchmark no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/benchmarks/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(BenchmarkCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/benchmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"cpuId":1,"gpuId":2,"videojuegoId":3,"resolucionId":4,"fpsPromedio":95.5}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(BenchmarkUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/benchmarks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"cpuId":1,"gpuId":2,"videojuegoId":3,"resolucionId":4,"fpsPromedio":95.5}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/benchmarks/1"))
                .andExpect(status().isNoContent());
        verify(service, times(1)).eliminar(1);
    }
}