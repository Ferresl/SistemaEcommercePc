package com.DuocucEcommerce.Carrito.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.Carrito.Dto.ItemCarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Model.ItemCarrito;
import com.DuocucEcommerce.Carrito.Repository.ItemCarritoRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class ItemCarritoServiceTest {

    @Mock
    private ItemCarritoRepository repository; // repositorio simulado

    @InjectMocks
    private ItemCarritoService service; // servicio real con el repo simulado inyectado

    private ItemCarrito itemEjemplo;
    private ItemCarritoCreateDTO createDTO;
    private ItemCarritoUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        itemEjemplo = ItemCarrito.builder()
                .id(1)
                .carritoId(10)
                .productoId(20)
                .configuracionId(30)
                .cantidad(2)
                .precioUnitario(new BigDecimal("1000"))
                .subtotal(new BigDecimal("2000"))
                .build();

        createDTO = ItemCarritoCreateDTO.builder()
                .carritoId(10)
                .productoId(20)
                .configuracionId(30)
                .cantidad(2)
                .precioUnitario(new BigDecimal("1000"))
                .subtotal(new BigDecimal("2000"))
                .build();

        updateDTO = ItemCarritoUpdateDTO.builder()
                .carritoId(10)
                .productoId(20)
                .configuracionId(30)
                .cantidad(2)
                .precioUnitario(new BigDecimal("1000"))
                .subtotal(new BigDecimal("2000"))
                .build();
    }

    @Test
    void listar_retornaListaConItems() {
        // ARRANGE
        List<ItemCarrito> listaFalsa = new ArrayList<>();
        listaFalsa.add(itemEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<ItemCarritoResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(20, resultado.get(0).getProductoId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(itemEjemplo));

        // ACT
        ItemCarritoResponseDTO resultado = service.buscarPorId(1);

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

        assertEquals("ItemCarrito no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaItemGuardado() {
        // ARRANGE
        when(repository.save(any(ItemCarrito.class))).thenReturn(itemEjemplo);

        // ACT
        ItemCarritoResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(2, resultado.getCantidad());
        verify(repository, times(1)).save(any(ItemCarrito.class));
    }

    @Test
    void actualizar_retornaItemActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(itemEjemplo));
        when(repository.save(itemEjemplo)).thenReturn(itemEjemplo);

        // ACT
        ItemCarritoResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals(new BigDecimal("2000"), resultado.getSubtotal());
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(itemEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(itemEjemplo);
    }
}