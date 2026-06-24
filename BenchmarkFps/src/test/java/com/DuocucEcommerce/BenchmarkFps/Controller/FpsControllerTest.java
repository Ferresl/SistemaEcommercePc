package com.DuocucEcommerce.BenchmarkFps.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionRequestDTO;
import com.DuocucEcommerce.BenchmarkFps.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.BenchmarkFps.Service.FpsService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class FpsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FpsService service;

    @InjectMocks
    private FpsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void estimar_retorna200() throws Exception {
        // ARRANGE
        when(service.estimar(any(EstimacionRequestDTO.class)))
                .thenReturn(EstimacionFPSResponseDTO.builder().id(1).configuracionId(1).fpsEstimado(95.5).build());

        // ACT + ASSERT
        mockMvc.perform(post("/api/fps/estimar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"configuracionId\":1,\"cpuId\":2,\"gpuId\":3,\"videojuegoId\":4,\"resolucionId\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fpsEstimado").value(95.5));
    }
}