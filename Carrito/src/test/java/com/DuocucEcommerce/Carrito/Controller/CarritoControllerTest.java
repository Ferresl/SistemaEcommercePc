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

import com.DuocucEcommerce.Carrito.Dto.ActualizarCantidadItemDTO;
import com.DuocucEcommerce.Carrito.Dto.AgregarItemCarritoDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Service.CarritoService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class CarritoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarritoService service;

    @InjectMocks
    private CarritoController controller;

    private CarritoResponseDTO carrito;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        carrito = CarritoResponseDTO.builder().id(1).usuarioId(10).subtotal(new BigDecimal("2000")).total(new BigDecimal("2000")).items(List.of()).build();
    }

    @Test void listar_retorna200ConCarritos() throws Exception { when(service.listar()).thenReturn(List.of(carrito)); mockMvc.perform(get("/api/carritos")).andExpect(status().isOk()).andExpect(jsonPath("$[0].usuarioId").value(10)); }
    @Test void buscarPorId_retorna200() throws Exception { when(service.buscarPorId(1)).thenReturn(carrito); mockMvc.perform(get("/api/carritos/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)); }
    @Test void buscarPorId_retorna404() throws Exception { when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Carrito no encontrado con id 99")); mockMvc.perform(get("/api/carritos/99")).andExpect(status().isNotFound()); }
    @Test void buscarPorUsuario_retorna200() throws Exception { when(service.buscarPorUsuario(10)).thenReturn(carrito); mockMvc.perform(get("/api/carritos/usuario/10")).andExpect(status().isOk()).andExpect(jsonPath("$.usuarioId").value(10)); }
    @Test void crear_retorna201() throws Exception { when(service.crear(any(CarritoCreateDTO.class))).thenReturn(carrito); mockMvc.perform(post("/api/carritos").contentType(MediaType.APPLICATION_JSON).content("{\"usuarioId\":10}")).andExpect(status().isCreated()).andExpect(jsonPath("$.usuarioId").value(10)); }
    @Test void actualizar_retorna200() throws Exception { when(service.actualizar(eq(1), any(CarritoUpdateDTO.class))).thenReturn(carrito); mockMvc.perform(put("/api/carritos/1").contentType(MediaType.APPLICATION_JSON).content("{\"usuarioId\":10}")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)); }
    @Test void agregarItem_retorna201() throws Exception { when(service.agregarItem(eq(1), any(AgregarItemCarritoDTO.class))).thenReturn(carrito); mockMvc.perform(post("/api/carritos/1/items").contentType(MediaType.APPLICATION_JSON).content("{\"productoId\":20,\"configuracionId\":30,\"cantidad\":2}")).andExpect(status().isCreated()).andExpect(jsonPath("$.total").value(2000)); }
    @Test void actualizarCantidad_retorna200() throws Exception { when(service.actualizarCantidad(eq(5), any(ActualizarCantidadItemDTO.class))).thenReturn(carrito); mockMvc.perform(put("/api/carritos/items/5").contentType(MediaType.APPLICATION_JSON).content("{\"cantidad\":2}")).andExpect(status().isOk()).andExpect(jsonPath("$.total").value(2000)); }
    @Test void eliminarItem_retorna204() throws Exception { doNothing().when(service).eliminarItem(5); mockMvc.perform(delete("/api/carritos/items/5")).andExpect(status().isNoContent()); verify(service, times(1)).eliminarItem(5); }
    @Test void vaciar_retorna204() throws Exception { doNothing().when(service).vaciar(1); mockMvc.perform(delete("/api/carritos/1/vaciar")).andExpect(status().isNoContent()); verify(service, times(1)).vaciar(1); }
    @Test void eliminar_retorna204() throws Exception { doNothing().when(service).eliminar(1); mockMvc.perform(delete("/api/carritos/1")).andExpect(status().isNoContent()); verify(service, times(1)).eliminar(1); }
}