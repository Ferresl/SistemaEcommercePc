package com.DuocucEcommerce.Comparador.Service;

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

import com.DuocucEcommerce.Comparador.Dto.ItemComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionUpdateDTO;
import com.DuocucEcommerce.Comparador.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Comparador.Model.ItemComparacion;
import com.DuocucEcommerce.Comparador.Repository.ItemComparacionRepository;

@ExtendWith(MockitoExtension.class)
class ItemComparacionServiceTest {

    @Mock
    private ItemComparacionRepository repository;

    @InjectMocks
    private ItemComparacionService service;

    private ItemComparacion item;

    @BeforeEach
    void setUp() {
        item = ItemComparacion.builder().id(1).comparacionId(10).productoId(20).build();
    }

    @Test
    void listarRetornaItems() {
        when(repository.findAll()).thenReturn(List.of(item));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals(20, respuesta.get(0).getProductoId());
        verify(repository).findAll();
    }

    @Test
    void buscarPorIdRetornaItem() {
        when(repository.findById(1)).thenReturn(Optional.of(item));

        var respuesta = service.buscarPorId(1);

        assertEquals(10, respuesta.getComparacionId());
        assertEquals(20, respuesta.getProductoId());
        verify(repository).findById(1);
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void crearGuardaItem() {
        var dto = ItemComparacionCreateDTO.builder().comparacionId(10).productoId(20).build();
        when(repository.save(any(ItemComparacion.class))).thenReturn(item);

        var respuesta = service.crear(dto);

        assertEquals(1, respuesta.getId());
        assertEquals(20, respuesta.getProductoId());
        verify(repository).save(any(ItemComparacion.class));
    }

    @Test
    void actualizarModificaItem() {
        var dto = ItemComparacionUpdateDTO.builder().comparacionId(11).productoId(21).build();
        var actualizado = ItemComparacion.builder().id(1).comparacionId(11).productoId(21).build();
        when(repository.findById(1)).thenReturn(Optional.of(item));
        when(repository.save(any(ItemComparacion.class))).thenReturn(actualizado);

        var respuesta = service.actualizar(1, dto);

        assertEquals(11, respuesta.getComparacionId());
        assertEquals(21, respuesta.getProductoId());
        verify(repository).save(item);
    }

    @Test
    void eliminarBorraItem() {
        when(repository.findById(1)).thenReturn(Optional.of(item));

        service.eliminar(1);

        verify(repository).delete(item);
    }
}