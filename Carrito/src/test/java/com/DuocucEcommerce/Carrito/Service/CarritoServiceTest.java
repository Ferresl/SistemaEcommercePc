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

import com.DuocucEcommerce.Carrito.Client.InventarioClient;
import com.DuocucEcommerce.Carrito.Client.InventarioResponseDTO;
import com.DuocucEcommerce.Carrito.Client.ProductoClient;
import com.DuocucEcommerce.Carrito.Client.ProductoResponseDTO;
import com.DuocucEcommerce.Carrito.Client.UsuarioClient;
import com.DuocucEcommerce.Carrito.Dto.ActualizarCantidadItemDTO;
import com.DuocucEcommerce.Carrito.Dto.AgregarItemCarritoDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Exception.BadRequestException;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Model.Carrito;
import com.DuocucEcommerce.Carrito.Model.ItemCarrito;
import com.DuocucEcommerce.Carrito.Repository.CarritoRepository;
import com.DuocucEcommerce.Carrito.Repository.ItemCarritoRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class CarritoServiceTest {

    @Mock private CarritoRepository carritoRepository;
    @Mock private ItemCarritoRepository itemRepository;
    @Mock private UsuarioClient usuarioClient;
    @Mock private ProductoClient productoClient;
    @Mock private InventarioClient inventarioClient;

    @InjectMocks
    private CarritoService service;

    private Carrito carritoEjemplo;
    private ItemCarrito itemEjemplo;

    @BeforeEach
    void setUp() {
        carritoEjemplo = Carrito.builder().id(1).usuarioId(10).subtotal(BigDecimal.ZERO).total(BigDecimal.ZERO).build();
        itemEjemplo = ItemCarrito.builder().id(5).carritoId(1).productoId(20).configuracionId(30).cantidad(2)
                .precioUnitario(new BigDecimal("1000")).subtotal(new BigDecimal("2000")).build();
    }

    @Test
    void listar_retornaListaConCarritos() {
        // ARRANGE
        List<Carrito> listaFalsa = new ArrayList<>();
        listaFalsa.add(carritoEjemplo);
        when(carritoRepository.findAll()).thenReturn(listaFalsa);
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT
        List<CarritoResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getUsuarioId());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT
        CarritoResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(carritoRepository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Carrito no encontrado con id 99", ex.getMessage());
    }

    @Test
    void buscarPorUsuario_encontrado() {
        // ARRANGE
        when(carritoRepository.findByUsuarioId(10)).thenReturn(Optional.of(carritoEjemplo));
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT
        CarritoResponseDTO resultado = service.buscarPorUsuario(10);

        // ASSERT
        assertEquals(10, resultado.getUsuarioId());
    }

    @Test
    void crear_retornaCarritoGuardado() {
        // ARRANGE
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carritoEjemplo);
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT
        CarritoResponseDTO resultado = service.crear(CarritoCreateDTO.builder().usuarioId(10).build());

        // ASSERT
        assertEquals(10, resultado.getUsuarioId());
        verify(usuarioClient, times(1)).obtenerPorId(10);
    }

    @Test
    void actualizar_retornaCarritoActualizado() {
        // ARRANGE
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(carritoRepository.save(carritoEjemplo)).thenReturn(carritoEjemplo);
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT
        CarritoResponseDTO resultado = service.actualizar(1, CarritoUpdateDTO.builder().usuarioId(10).build());

        // ASSERT
        assertEquals(10, resultado.getUsuarioId());
        verify(usuarioClient, times(1)).obtenerPorId(10);
    }

    @Test
    void agregarItem_recalculaCarrito() {
        // ARRANGE
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(productoClient.obtenerPorId(20)).thenReturn(new ProductoResponseDTO(20, "Producto", new BigDecimal("1000"), 1));
        when(inventarioClient.obtenerPorProducto(20)).thenReturn(new InventarioResponseDTO(1, 20, 10, 0, 1));
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of(itemEjemplo));
        when(carritoRepository.save(carritoEjemplo)).thenReturn(carritoEjemplo);

        // ACT
        CarritoResponseDTO resultado = service.agregarItem(1, AgregarItemCarritoDTO.builder().productoId(20).configuracionId(30).cantidad(2).build());

        // ASSERT
        assertEquals(new BigDecimal("2000"), resultado.getTotal());
        verify(itemRepository, times(1)).save(any(ItemCarrito.class));
    }

    @Test
    void agregarItem_stockInsuficiente_lanzaBadRequest() {
        // ARRANGE
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(productoClient.obtenerPorId(20)).thenReturn(new ProductoResponseDTO(20, "Producto", new BigDecimal("1000"), 1));
        when(inventarioClient.obtenerPorProducto(20)).thenReturn(new InventarioResponseDTO(1, 20, 1, 0, 1));

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.agregarItem(1, AgregarItemCarritoDTO.builder().productoId(20).configuracionId(30).cantidad(2).build());
        });

        assertEquals("Stock insuficiente", ex.getMessage());
    }

    @Test
    void actualizarCantidad_recalculaCarrito() {
        // ARRANGE
        when(itemRepository.findById(5)).thenReturn(Optional.of(itemEjemplo));
        when(inventarioClient.obtenerPorProducto(20)).thenReturn(new InventarioResponseDTO(1, 20, 10, 0, 1));
        when(itemRepository.save(itemEjemplo)).thenReturn(itemEjemplo);
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of(itemEjemplo));
        when(carritoRepository.save(carritoEjemplo)).thenReturn(carritoEjemplo);

        // ACT
        CarritoResponseDTO resultado = service.actualizarCantidad(5, new ActualizarCantidadItemDTO(3));

        // ASSERT
        assertEquals(new BigDecimal("3000"), resultado.getTotal());
    }

    @Test
    void eliminarItem_exitoso() {
        // ARRANGE
        when(itemRepository.findById(5)).thenReturn(Optional.of(itemEjemplo));
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of());

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminarItem(5));
        verify(itemRepository, times(1)).delete(itemEjemplo);
    }

    @Test
    void vaciar_exitoso() {
        // ARRANGE
        when(itemRepository.findByCarritoId(1)).thenReturn(List.of(itemEjemplo), List.of());
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.vaciar(1));
        verify(itemRepository, times(1)).deleteAll(List.of(itemEjemplo));
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carritoEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(carritoRepository, times(1)).delete(carritoEjemplo);
    }
}