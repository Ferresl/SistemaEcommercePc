package com.DuocucEcommerce.Compatibilidad.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.ConflictoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ConflictoCompatibilidadRepository;

@ExtendWith(MockitoExtension.class)
class ConflictoCompatibilidadServiceTest {

    @Mock
    private ConflictoCompatibilidadRepository repository;

    @InjectMocks
    private ConflictoCompatibilidadService service;

    private ConflictoCompatibilidad conflicto;

    @BeforeEach
    void setUp() {
        conflicto = ConflictoCompatibilidad.builder()
                .id(1).resultadoId(2).productoAId(10).productoBId(20).motivo("Motivo")
                .build();
    }

    @Test
    void listarRetornaConflictos() {
        when(repository.findAll()).thenReturn(List.of(conflicto));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals("Motivo", respuesta.get(0).getMotivo());
    }

    @Test
    void buscarPorIdRetornaConflicto() {
        when(repository.findById(1)).thenReturn(Optional.of(conflicto));

        var respuesta = service.buscarPorId(1);

        assertEquals(2, respuesta.getResultadoId());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void crearGuardaConflicto() {
        var dto = ConflictoCompatibilidadCreateDTO.builder()
                .resultadoId(2).productoAId(10).productoBId(20).motivo("Motivo")
                .build();
        when(repository.save(any(ConflictoCompatibilidad.class))).thenReturn(conflicto);

        var respuesta = service.crear(dto);

        assertEquals(1, respuesta.getId());
        verify(repository).save(any(ConflictoCompatibilidad.class));
    }

    @Test
    void actualizarModificaConflicto() {
        var dto = ConflictoCompatibilidadUpdateDTO.builder()
                .resultadoId(3).productoAId(11).productoBId(21).motivo("Nuevo")
                .build();
        when(repository.findById(1)).thenReturn(Optional.of(conflicto));
        when(repository.save(any(ConflictoCompatibilidad.class))).thenReturn(conflicto);

        var respuesta = service.actualizar(1, dto);

        assertEquals("Nuevo", respuesta.getMotivo());
        verify(repository).save(conflicto);
    }

    @Test
    void eliminarBorraConflicto() {
        when(repository.findById(1)).thenReturn(Optional.of(conflicto));

        service.eliminar(1);

        verify(repository).delete(conflicto);
    }
}