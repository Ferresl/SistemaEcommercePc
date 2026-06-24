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

import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.RecomendacionComponente;
import com.DuocucEcommerce.Compatibilidad.Repository.RecomendacionComponenteRepository;

@ExtendWith(MockitoExtension.class)
class RecomendacionComponenteServiceTest {

    @Mock
    private RecomendacionComponenteRepository repository;

    @InjectMocks
    private RecomendacionComponenteService service;

    private RecomendacionComponente recomendacion;

    @BeforeEach
    void setUp() {
        recomendacion = RecomendacionComponente.builder()
                .id(1).conflictoId(2).productoRecomendadoId(30).motivo("Revisar")
                .build();
    }

    @Test
    void listarRetornaRecomendaciones() {
        when(repository.findAll()).thenReturn(List.of(recomendacion));

        assertEquals(1, service.listar().size());
    }

    @Test
    void buscarPorIdRetornaRecomendacion() {
        when(repository.findById(1)).thenReturn(Optional.of(recomendacion));

        assertEquals(30, service.buscarPorId(1).getProductoRecomendadoId());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void crearGuardaRecomendacion() {
        var dto = RecomendacionComponenteCreateDTO.builder()
                .conflictoId(2).productoRecomendadoId(30).motivo("Revisar")
                .build();
        when(repository.save(any(RecomendacionComponente.class))).thenReturn(recomendacion);

        assertEquals(1, service.crear(dto).getId());
    }

    @Test
    void actualizarModificaRecomendacion() {
        var dto = RecomendacionComponenteUpdateDTO.builder()
                .conflictoId(3).productoRecomendadoId(31).motivo("Nuevo")
                .build();
        when(repository.findById(1)).thenReturn(Optional.of(recomendacion));
        when(repository.save(any(RecomendacionComponente.class))).thenReturn(recomendacion);

        assertEquals("Nuevo", service.actualizar(1, dto).getMotivo());
    }

    @Test
    void eliminarBorraRecomendacion() {
        when(repository.findById(1)).thenReturn(Optional.of(recomendacion));

        service.eliminar(1);

        verify(repository).delete(recomendacion);
    }
}