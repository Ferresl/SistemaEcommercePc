package com.DuocucEcommerce.Compatibilidad.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Compatibilidad.Service.ConflictoCompatibilidadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ConflictoCompatibilidadControllerTest {

    @Mock
    private ConflictoCompatibilidadService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ConflictoCompatibilidadResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConflictoCompatibilidadController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = ConflictoCompatibilidadResponseDTO.builder()
                .id(1).resultadoId(2).productoAId(10).productoBId(20).motivo("Motivo")
                .build();
    }

    @Test
    void listarRetornaOk() throws Exception {
        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/compatibilidad/conflictos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].motivo").value("Motivo"));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/compatibilidad/conflictos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoAId").value(10));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        var dto = ConflictoCompatibilidadCreateDTO.builder()
                .resultadoId(2).productoAId(10).productoBId(20).motivo("Motivo")
                .build();
        when(service.crear(any())).thenReturn(response);

        mockMvc.perform(post("/api/compatibilidad/conflictos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizarRetornaOk() throws Exception {
        when(service.actualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/compatibilidad/conflictos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultadoId").value(2));
    }

    @Test
    void eliminarRetornaNoContent() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/api/compatibilidad/conflictos/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1);
    }
}