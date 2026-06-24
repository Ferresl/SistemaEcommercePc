package com.DuocucEcommerce.Carrito.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
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

import com.DuocucEcommerce.Carrito.Dto.ItemCarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Service.ItemCarritoService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class ItemCarritoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemCarritoService service;

    @InjectMocks
    private ItemCarritoController controller;

    private ItemCarritoResponseDTO item;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        item = ItemCarritoResponseDTO.builder().id(1).carritoId(10).productoId(20).configuracionId(30).cantidad(2)
                .precioUnitario(new BigDecimal("1000")).subtotal(new BigDecimal("2000")).build();
    }

    @Test void listar_retorna200ConItems() throws Exception { when(service.listar()).thenReturn(List.of(item)); mockMvc.perform(get("/api/items-carrito")).andExpect(status().isOk()).andExpect(jsonPath("$[0].productoId").value(20)); }
    @Test void buscarPorId_retorna200() throws Exception { when(service.buscarPorId(1)).thenReturn(item); mockMvc.perform(get("/api/items-carrito/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)); }
    @Test void buscarPorId_retorna404() throws Exception { when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("ItemCarrito no encontrado con id 99")); mockMvc.perform(get("/api/items-carrito/99")).andExpect(status().isNotFound()); }
    @Test void crear_retorna201() throws Exception { when(service.crear(any(ItemCarritoCreateDTO.class))).thenReturn(item); mockMvc.perform(post("/api/items-carrito").contentType(MediaType.APPLICATION_JSON).content("{\"carritoId\":10,\"productoId\":20,\"configuracionId\":30,\"cantidad\":2,\"precioUnitario\":1000,\"subtotal\":2000}")).andExpect(status().isCreated()).andExpect(jsonPath("$.cantidad").value(2)); }
    @Test void actualizar_retorna200() throws Exception { when(service.actualizar(eq(1), any(ItemCarritoUpdateDTO.class))).thenReturn(item); mockMvc.perform(put("/api/items-carrito/1").contentType(MediaType.APPLICATION_JSON).content("{\"carritoId\":10,\"productoId\":20,\"configuracionId\":30,\"cantidad\":2,\"precioUnitario\":1000,\"subtotal\":2000}")).andExpect(status().isOk()).andExpect(jsonPath("$.subtotal").value(2000)); }
    @Test void eliminar_retorna204() throws Exception { doNothing().when(service).eliminar(1); mockMvc.perform(delete("/api/items-carrito/1")).andExpect(status().isNoContent()); verify(service, times(1)).eliminar(1); }
}