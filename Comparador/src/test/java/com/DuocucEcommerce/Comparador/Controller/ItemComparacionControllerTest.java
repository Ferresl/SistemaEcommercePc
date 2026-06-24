package com.DuocucEcommerce.Comparador.Controller;

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

import com.DuocucEcommerce.Comparador.Dto.ItemComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Comparador.Service.ItemComparacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ItemComparacionControllerTest {

    @Mock
    private ItemComparacionService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ItemComparacionResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ItemComparacionController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = ItemComparacionResponseDTO.builder().id(1).comparacionId(10).productoId(20).build();
    }

    @Test
    void listarRetornaOk() throws Exception {
        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/comparaciones/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(20));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/comparaciones/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comparacionId").value(10));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        when(service.crear(any())).thenReturn(response);

        mockMvc.perform(post("/api/comparaciones/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(20));
    }

    @Test
    void actualizarRetornaOk() throws Exception {
        when(service.actualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/comparaciones/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminarRetornaNoContent() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/api/comparaciones/items/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1);
    }
}