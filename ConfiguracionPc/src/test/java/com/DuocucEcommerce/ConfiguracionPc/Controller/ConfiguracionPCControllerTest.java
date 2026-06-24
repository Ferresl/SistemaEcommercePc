package com.DuocucEcommerce.ConfiguracionPc.Controller;

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

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.ConfiguracionPc.Service.ConfiguracionPCService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ConfiguracionPCControllerTest {

    @Mock
    private ConfiguracionPCService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ConfiguracionPCResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConfiguracionPCController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = ConfiguracionPCResponseDTO.builder()
                .id(1).usuarioId(2).nombre("PC Gamer")
                .cpuId(10).gpuId(20).ramId(30).placaMadreId(40).almacenamientoId(50)
                .fuentePoderId(60).gabineteId(70)
                .precioTotal(new BigDecimal("7000")).tdpTotal(300).estado("BORRADOR")
                .build();
    }

    @Test
    void listarRetornaOk() throws Exception {
        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/configuraciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("PC Gamer"));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/configuraciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(2));
    }

    @Test
    void listarPorUsuarioRetornaOk() throws Exception {
        when(service.listarPorUsuario(2)).thenReturn(List.of(response));

        mockMvc.perform(get("/api/configuraciones/usuario/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("BORRADOR"));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        when(service.crear(any())).thenReturn(response);

        mockMvc.perform(post("/api/configuraciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crearDto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizarRetornaOk() throws Exception {
        when(service.actualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/configuraciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tdpTotal").value(300));
    }

    @Test
    void evaluarRetornaOk() throws Exception {
        when(service.evaluar(1)).thenReturn(response);

        mockMvc.perform(post("/api/configuraciones/1/evaluar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("BORRADOR"));
    }

    @Test
    void eliminarRetornaNoContent() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/api/configuraciones/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1);
    }

    private ConfiguracionPCCreateDTO crearDto() {
        return ConfiguracionPCCreateDTO.builder()
                .usuarioId(2).nombre("PC Gamer")
                .cpuId(10).gpuId(20).ramId(30).placaMadreId(40).almacenamientoId(50)
                .fuentePoderId(60).gabineteId(70)
                .build();
    }
}