package com.DuocucEcommerce.Inventario.Controller;

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

import com.DuocucEcommerce.Inventario.Dto.InventarioCreateDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioResponseDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioUpdateDTO;
import com.DuocucEcommerce.Inventario.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Inventario.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Inventario.Service.InventarioService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class InventarioControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private InventarioService service; // servicio falso

    @InjectMocks
    private InventarioController controller;

    private InventarioResponseDTO inventarioEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        inventarioEjemplo = InventarioResponseDTO.builder()
                .id(1)
                .productoId(10)
                .nombreProducto("Procesador Ryzen 5 7600")
                .stockDisponible(15)
                .stockReservado(1)
                .stockMinimo(2)
                .build();
    }

    @Test
    void listar_retorna200ConInventarios() throws Exception {
        // ARRANGE
        List<InventarioResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(inventarioEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/inventarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(10))
                .andExpect(jsonPath("$[0].nombreProducto").value("Procesador Ryzen 5 7600"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/inventarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Inventario no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/inventarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorProducto_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorProducto(10)).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/inventarios/producto/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10))
                .andExpect(jsonPath("$.nombreProducto").value("Procesador Ryzen 5 7600"));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(InventarioCreateDTO.class))).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/inventarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productoId\":10,\"stockDisponible\":15,\"stockReservado\":1,\"stockMinimo\":2}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.stockDisponible").value(15));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(InventarioUpdateDTO.class))).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/inventarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productoId\":10,\"stockDisponible\":15,\"stockReservado\":1,\"stockMinimo\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stockMinimo").value(2));
    }

    @Test
    void descontar_retorna200() throws Exception {
        // ARRANGE
        when(service.descontar(10, 5)).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/inventarios/descontar/10").param("cantidad", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void reponer_retorna200() throws Exception {
        // ARRANGE
        when(service.reponer(10, 5)).thenReturn(inventarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/inventarios/reponer/10").param("cantidad", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/inventarios/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}
