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

import com.DuocucEcommerce.Compatibilidad.Dto.ResultadoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ResultadoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.ResultadoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ResultadoCompatibilidadRepository;

@ExtendWith(MockitoExtension.class)
class ResultadoCompatibilidadServiceTest {

    @Mock
    private ResultadoCompatibilidadRepository repository;

    @InjectMocks
    private ResultadoCompatibilidadService service;

    private ResultadoCompatibilidad resultado;

    @BeforeEach
    void setUp() {
        resultado = ResultadoCompatibilidad.builder()
                .id(1).configuracionId(2).estado("COMPATIBLE").mensaje("Ok")
                .build();
    }

    @Test
    void listarRetornaResultados() {
        when(repository.findAll()).thenReturn(List.of(resultado));

        assertEquals(1, service.listar().size());
    }

    @Test
    void buscarPorIdRetornaResultado() {
        when(repository.findById(1)).thenReturn(Optional.of(resultado));

        assertEquals("COMPATIBLE", service.buscarPorId(1).getEstado());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void crearGuardaResultado() {
        var dto = ResultadoCompatibilidadCreateDTO.builder().configuracionId(2).estado("COMPATIBLE").mensaje("Ok").build();
        when(repository.save(any(ResultadoCompatibilidad.class))).thenReturn(resultado);

        assertEquals(1, service.crear(dto).getId());
    }

    @Test
    void actualizarModificaResultado() {
        var dto = ResultadoCompatibilidadUpdateDTO.builder().configuracionId(3).estado("INCOMPATIBLE").mensaje("No").build();
        when(repository.findById(1)).thenReturn(Optional.of(resultado));
        when(repository.save(any(ResultadoCompatibilidad.class))).thenReturn(resultado);

        assertEquals("INCOMPATIBLE", service.actualizar(1, dto).getEstado());
    }

    @Test
    void eliminarBorraResultado() {
        when(repository.findById(1)).thenReturn(Optional.of(resultado));

        service.eliminar(1);

        verify(repository).delete(resultado);
    }
}