package com.DuocucEcommerce.Notificacion.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.Notificacion.Dto.NotificacionCreateDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionResponseDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionUpdateDTO;
import com.DuocucEcommerce.Notificacion.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Notificacion.Model.Notificacion;
import com.DuocucEcommerce.Notificacion.Repository.NotificacionRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository; // repositorio simulado

    @InjectMocks
    private NotificacionService service; // servicio real con el repo simulado inyectado

    private Notificacion notificacionEjemplo;
    private NotificacionCreateDTO createDTO;
    private NotificacionUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        notificacionEjemplo = new Notificacion();
        notificacionEjemplo.setId(1);
        notificacionEjemplo.setUsuarioId(10);
        notificacionEjemplo.setTitulo("Pedido confirmado");
        notificacionEjemplo.setMensaje("Tu pedido fue confirmado");
        notificacionEjemplo.setTipo("PEDIDO");
        notificacionEjemplo.setLeida(false);

        createDTO = NotificacionCreateDTO.builder()
                .usuarioId(10)
                .titulo("Pedido confirmado")
                .mensaje("Tu pedido fue confirmado")
                .tipo("PEDIDO")
                .leida(false)
                .build();

        updateDTO = NotificacionUpdateDTO.builder()
                .usuarioId(10)
                .titulo("Pedido confirmado")
                .mensaje("Tu pedido fue confirmado")
                .tipo("PEDIDO")
                .leida(false)
                .build();
    }

    @Test
    void listar_retornaListaConNotificaciones() {
        // ARRANGE
        List<Notificacion> listaFalsa = new ArrayList<>();
        listaFalsa.add(notificacionEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<NotificacionResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("Pedido confirmado", resultado.get(0).getTitulo());
    }

    @Test
    void listarPorUsuario_retornaNotificaciones() {
        // ARRANGE
        when(repository.findByUsuarioId(10)).thenReturn(List.of(notificacionEjemplo));

        // ACT
        List<NotificacionResponseDTO> resultado = service.listarPorUsuario(10);

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getUsuarioId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(notificacionEjemplo));

        // ACT
        NotificacionResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Notificacion no encontrada con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaNotificacionGuardada() {
        // ARRANGE
        when(repository.save(any(Notificacion.class))).thenReturn(notificacionEjemplo);

        // ACT
        NotificacionResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals("PEDIDO", resultado.getTipo());
        verify(repository, times(1)).save(any(Notificacion.class));
    }

    @Test
    void actualizar_retornaNotificacionActualizada() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(notificacionEjemplo));
        when(repository.save(notificacionEjemplo)).thenReturn(notificacionEjemplo);

        // ACT
        NotificacionResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals("Pedido confirmado", resultado.getTitulo());
    }

    @Test
    void marcarLeida_retornaNotificacionLeida() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(notificacionEjemplo));
        when(repository.save(notificacionEjemplo)).thenReturn(notificacionEjemplo);

        // ACT
        NotificacionResponseDTO resultado = service.marcarLeida(1);

        // ASSERT
        assertTrue(resultado.getLeida());
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(notificacionEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(notificacionEjemplo);
    }
}