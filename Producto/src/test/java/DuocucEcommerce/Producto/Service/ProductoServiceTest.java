package DuocucEcommerce.Producto.Service;

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

import DuocucEcommerce.Producto.Client.CategoriaClient;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoCreateDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoResponseDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoUpdateDTO;
import DuocucEcommerce.Producto.Exception.BadRequestException;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.Producto;
import DuocucEcommerce.Producto.Repository.ProductoRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository; // repositorio simulado

    @Mock
    private CategoriaClient categoriaClient; // cliente simulado

    @InjectMocks
    private ProductoService service; // servicio real con mocks inyectados

    private Producto productoEjemplo;
    private ProductoCreateDTO createDTO;
    private ProductoUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        productoEjemplo = new Producto();
        productoEjemplo.setId(1);
        productoEjemplo.setNombre("Procesador Ryzen 5 7600");
        productoEjemplo.setMarca("AMD");
        productoEjemplo.setModelo("Ryzen 5 7600");
        productoEjemplo.setPrecio(new BigDecimal("249990"));
        productoEjemplo.setCategoriaId(1);
        productoEjemplo.setEstado("ACTIVO");

        createDTO = ProductoCreateDTO.builder()
                .nombre("Procesador Ryzen 5 7600")
                .marca("AMD")
                .modelo("Ryzen 5 7600")
                .precio(new BigDecimal("249990"))
                .categoriaId(1)
                .estado("ACTIVO")
                .build();

        updateDTO = ProductoUpdateDTO.builder()
                .nombre("Procesador Ryzen 5 7600")
                .marca("AMD")
                .modelo("Ryzen 5 7600")
                .precio(new BigDecimal("249990"))
                .categoriaId(1)
                .estado("ACTIVO")
                .build();
    }

    @Test
    void listar_retornaListaConProductos() {
        // ARRANGE: creamos la lista manualmente
        List<Producto> listaFalsa = new ArrayList<>();
        listaFalsa.add(productoEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT: llamamos al metodo real del servicio
        List<ProductoResponseDTO> resultado = service.listar();

        // ASSERT: verificamos el resultado
        assertEquals(1, resultado.size());
        assertEquals("Procesador Ryzen 5 7600", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE: el repositorio devuelve un Optional con el producto
        when(repository.findById(1)).thenReturn(Optional.of(productoEjemplo));

        // ACT
        ProductoResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
        assertEquals("AMD", resultado.getMarca());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE: el repositorio devuelve un Optional vacio
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Producto no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaProductoGuardado() {
        // ARRANGE: el repositorio devuelve el producto al guardarlo
        when(repository.save(any(Producto.class))).thenReturn(productoEjemplo);

        // ACT
        ProductoResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals("Procesador Ryzen 5 7600", resultado.getNombre());
        verify(categoriaClient, times(1)).obtenerPorId(1);
        verify(repository, times(1)).save(any(Producto.class));
    }

    @Test
    void crear_precioInvalido_lanzaBadRequest() {
        // ARRANGE
        createDTO.setPrecio(BigDecimal.ZERO);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.crear(createDTO);
        });

        assertEquals("El precio debe ser mayor a 0", ex.getMessage());
        verify(repository, never()).save(any(Producto.class));
    }

    @Test
    void actualizar_retornaProductoActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(productoEjemplo));
        when(repository.save(productoEjemplo)).thenReturn(productoEjemplo);

        // ACT
        ProductoResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals("Ryzen 5 7600", resultado.getModelo());
        verify(categoriaClient, times(1)).obtenerPorId(1);
        verify(repository, times(1)).save(productoEjemplo);
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE: el producto existe
        when(repository.findById(1)).thenReturn(Optional.of(productoEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));

        verify(repository, times(1)).delete(productoEjemplo);
    }

    @Test
    void listarPorCategoria_retornaProductos() {
        // ARRANGE
        when(repository.findByCategoriaId(1)).thenReturn(List.of(productoEjemplo));

        // ACT
        List<ProductoResponseDTO> resultado = service.listarPorCategoria(1);

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getCategoriaId());
    }

    @Test
    void buscarPorTexto_retornaProductos() {
        // ARRANGE
        when(repository.findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCase("amd", "amd"))
                .thenReturn(List.of(productoEjemplo));

        // ACT
        List<ProductoResponseDTO> resultado = service.buscarPorTexto("amd");

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("AMD", resultado.get(0).getMarca());
    }

    @Test
    void filtrarPorPrecio_retornaProductos() {
        // ARRANGE
        BigDecimal minimo = new BigDecimal("100000");
        BigDecimal maximo = new BigDecimal("300000");
        when(repository.findByPrecioBetween(minimo, maximo)).thenReturn(List.of(productoEjemplo));

        // ACT
        List<ProductoResponseDTO> resultado = service.filtrarPorPrecio(minimo, maximo);

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(new BigDecimal("249990"), resultado.get(0).getPrecio());
    }
}
