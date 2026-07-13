package com.DuocucEcommerce.Inventario.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.DuocucEcommerce.Inventario.Client.ProductoClient;
import com.DuocucEcommerce.Inventario.Client.ProductoResponseDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioCreateDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioResponseDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioUpdateDTO;
import com.DuocucEcommerce.Inventario.Exception.BadRequestException;
import com.DuocucEcommerce.Inventario.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Inventario.Model.Inventario;
import com.DuocucEcommerce.Inventario.Repository.InventarioRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class InventarioServiceTest {

    @Mock
    private InventarioRepository repository; // repositorio simulado

    @Mock
    private ProductoClient productoClient; // cliente simulado

    @InjectMocks
    private InventarioService service; // servicio real con mocks inyectados

    private Inventario inventarioEjemplo;
    private InventarioCreateDTO createDTO;
    private InventarioUpdateDTO updateDTO;
    private ProductoResponseDTO productoEjemplo;

    @BeforeEach
    void setUp() {
        inventarioEjemplo = new Inventario();
        inventarioEjemplo.setId(1);
        inventarioEjemplo.setProductoId(10);
        inventarioEjemplo.setStockDisponible(15);
        inventarioEjemplo.setStockReservado(1);
        inventarioEjemplo.setStockMinimo(2);

        createDTO = InventarioCreateDTO.builder()
                .productoId(10)
                .stockDisponible(15)
                .stockReservado(1)
                .stockMinimo(2)
                .build();

        updateDTO = InventarioUpdateDTO.builder()
                .productoId(10)
                .stockDisponible(15)
                .stockReservado(1)
                .stockMinimo(2)
                .build();

        productoEjemplo = new ProductoResponseDTO();
        productoEjemplo.setId(10);
        productoEjemplo.setNombre("Procesador Ryzen 5 7600");
        lenient().when(productoClient.obtenerPorId(10)).thenReturn(productoEjemplo);
    }

    @Test
    void listar_retornaListaConInventarios() {
        // ARRANGE
        List<Inventario> listaFalsa = new ArrayList<>();
        listaFalsa.add(inventarioEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<InventarioResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getProductoId());
        assertEquals("Procesador Ryzen 5 7600", resultado.get(0).getNombreProducto());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(inventarioEjemplo));

        // ACT
        InventarioResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
        assertEquals("Procesador Ryzen 5 7600", resultado.getNombreProducto());

        verify(repository , times(1)).findById(1);
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Inventario no encontrado con id 99", ex.getMessage());

        verify(repository , times(1)).findById(99);
    }

    @Test
    void buscarPorProducto_encontrado() {
        // ARRANGE
        when(repository.findByProductoId(10)).thenReturn(Optional.of(inventarioEjemplo));

        // ACT
        InventarioResponseDTO resultado = service.buscarPorProducto(10);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
        assertEquals("Procesador Ryzen 5 7600", resultado.getNombreProducto());
    }

    @Test 
    void buscarPorProducto_noEncontrado() { 
        //ARRANGE
        when(repository.findByProductoId(99)).thenReturn(Optional.empty());

        //act 
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class , () -> {
            service.buscarPorProducto(99);
        });

        assertEquals("Inventario no encontrado para producto 99" , ex.getMessage());
        verify(repository , times(1)).findByProductoId(99);

        
    }

    @Test
    void crear_retornaInventarioGuardado() {
        // ARRANGE
        when(repository.save(any(Inventario.class))).thenReturn(inventarioEjemplo);

        // ACT
        InventarioResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(15, resultado.getStockDisponible());
        assertEquals("Procesador Ryzen 5 7600", resultado.getNombreProducto());
        verify(productoClient, times(1)).obtenerPorId(10);
        verify(repository, times(1)).save(any(Inventario.class));
    }

    @Test
    void crear_stockNegativo_lanzaBadRequest() {
        // ARRANGE
        createDTO.setStockDisponible(-1);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.crear(createDTO);
        });

        assertEquals("El stock no puede ser negativo", ex.getMessage());
        verify(repository, never()).save(any(Inventario.class));
    }

    @Test
    void actualizar_retornaInventarioActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(inventarioEjemplo));
        when(repository.save(inventarioEjemplo)).thenReturn(inventarioEjemplo);

        // ACT
        InventarioResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals(15, resultado.getStockDisponible());
        assertEquals("Procesador Ryzen 5 7600", resultado.getNombreProducto());
        verify(productoClient, times(1)).obtenerPorId(10);
    }

    @Test
    void descontar_restaStock() {
        // ARRANGE
        when(repository.findByProductoId(10)).thenReturn(Optional.of(inventarioEjemplo));
        when(repository.save(inventarioEjemplo)).thenReturn(inventarioEjemplo);

        // ACT
        InventarioResponseDTO resultado = service.descontar(10, 5);

        // ASSERT
        assertEquals(10, resultado.getStockDisponible());
    }

    @Test
    void descontar_stockInsuficiente_lanzaBadRequest() {
        // ARRANGE
        when(repository.findByProductoId(10)).thenReturn(Optional.of(inventarioEjemplo));

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.descontar(10, 50);
        });

        assertEquals("Stock insuficiente", ex.getMessage());
    }

    @Test
    void reponer_sumaStock() {
        // ARRANGE
        when(repository.findByProductoId(10)).thenReturn(Optional.of(inventarioEjemplo));
        when(repository.save(inventarioEjemplo)).thenReturn(inventarioEjemplo);

        // ACT
        InventarioResponseDTO resultado = service.reponer(10, 5);

        // ASSERT
        assertEquals(20, resultado.getStockDisponible());
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(inventarioEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(inventarioEjemplo);
    }
}
