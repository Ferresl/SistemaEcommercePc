package com.DuocucEcommerce.Compatibilidad.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadRequestDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Compatibilidad.Service.CompatibilidadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CompatibilidadControllerTest {

    @Mock
    private CompatibilidadService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CompatibilidadResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CompatibilidadController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = CompatibilidadResponseDTO.builder()
                .resultadoId(1).configuracionId(2).estado("COMPATIBLE").mensaje("Ok")
                .conflictos(List.of()).recomendaciones(List.of())
                .build();
    }

    @Test
    void evaluarRetornaOk() throws Exception {
        var dto = CompatibilidadRequestDTO.builder()
                .configuracionId(2).cpuId(10).gpuId(20).ramId(30).placaMadreId(40)
                .fuentePoderId(50).gabineteId(60).tdpTotal(450)
                .build();
        when(service.evaluar(any())).thenReturn(response);

        mockMvc.perform(post("/api/compatibilidad/evaluar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("COMPATIBLE"));
    }

    @Test
    void buscarPorConfiguracionRetornaOk() throws Exception {
        when(service.buscarPorConfiguracion(2)).thenReturn(response);

        mockMvc.perform(get("/api/compatibilidad/configuracion/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.configuracionId").value(2));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/compatibilidad/resultados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultadoId").value(1));
    }
}