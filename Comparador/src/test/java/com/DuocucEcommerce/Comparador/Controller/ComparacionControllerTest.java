package com.DuocucEcommerce.Comparador.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.DuocucEcommerce.Comparador.Dto.AgregarItemComparacionDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Comparador.Service.ComparacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ComparacionControllerTest {

    @Mock
    private ComparacionService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ComparacionResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ComparacionController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = ComparacionResponseDTO.builder().id(1).usuarioId(2).categoriaId(3).items(List.of()).build();
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/comparaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(2));
    }

    @Test
    void listarPorUsuarioRetornaOk() throws Exception {
        when(service.listarPorUsuario(2)).thenReturn(List.of(response));

        mockMvc.perform(get("/api/comparaciones/usuario/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoriaId").value(3));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        var dto = ComparacionCreateDTO.builder().usuarioId(2).categoriaId(3).build();
        when(service.crear(any())).thenReturn(response);

        mockMvc.perform(post("/api/comparaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void agregarItemRetornaCreated() throws Exception {
        when(service.agregarItem(any(), any())).thenReturn(response);

        mockMvc.perform(post("/api/comparaciones/1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new AgregarItemComparacionDTO(100))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoriaId").value(3));
    }

    @Test
    void eliminarItemRetornaNoContent() throws Exception {
        doNothing().when(service).eliminarItem(10);

        mockMvc.perform(delete("/api/comparaciones/items/10"))
                .andExpect(status().isNoContent());

        verify(service).eliminarItem(10);
    }
}