package com.DuocucEcommerce.Notificacion.Controller;

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

import com.DuocucEcommerce.Notificacion.Dto.NotificacionCreateDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionResponseDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionUpdateDTO;
import com.DuocucEcommerce.Notificacion.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Notificacion.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Notificacion.Service.NotificacionService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class NotificacionControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private NotificacionService service; // servicio falso

    @InjectMocks
    private NotificacionController controller;

    private NotificacionResponseDTO notificacionEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        notificacionEjemplo = NotificacionResponseDTO.builder()
                .id(1)
                .usuarioId(10)
                .titulo("Pedido confirmado")
                .mensaje("Tu pedido fue confirmado")
                .tipo("PEDIDO")
                .leida(false)
                .build();
    }

    @Test
    void listar_retorna200ConNotificaciones() throws Exception {
        // ARRANGE
        List<NotificacionResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(notificacionEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Pedido confirmado"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(notificacionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/notificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Notificacion no encontrada con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/notificaciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarPorUsuario_retorna200() throws Exception {
        // ARRANGE
        when(service.listarPorUsuario(10)).thenReturn(List.of(notificacionEjemplo));

        // ACT + ASSERT
        mockMvc.perform(get("/api/notificaciones/usuario/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(10));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(NotificacionCreateDTO.class))).thenReturn(notificacionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuarioId\":10,\"titulo\":\"Pedido confirmado\",\"mensaje\":\"Tu pedido fue confirmado\",\"tipo\":\"PEDIDO\",\"leida\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipo").value("PEDIDO"));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(NotificacionUpdateDTO.class))).thenReturn(notificacionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/notificaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuarioId\":10,\"titulo\":\"Pedido confirmado\",\"mensaje\":\"Tu pedido fue confirmado\",\"tipo\":\"PEDIDO\",\"leida\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Pedido confirmado"));
    }

    @Test
    void marcarLeida_retorna200() throws Exception {
        // ARRANGE
        NotificacionResponseDTO leida = NotificacionResponseDTO.builder()
                .id(1)
                .usuarioId(10)
                .titulo("Pedido confirmado")
                .mensaje("Tu pedido fue confirmado")
                .tipo("PEDIDO")
                .leida(true)
                .build();
        when(service.marcarLeida(1)).thenReturn(leida);

        // ACT + ASSERT
        mockMvc.perform(put("/api/notificaciones/1/leer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leida").value(true));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/notificaciones/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}