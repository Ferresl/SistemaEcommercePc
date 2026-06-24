package DuocucEcommerce.Producto.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
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

import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoCreateDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoResponseDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoUpdateDTO;
import DuocucEcommerce.Producto.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Service.ProductoService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class ProductoControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private ProductoService service; // servicio falso

    @InjectMocks
    private ProductoController controller;

    private ProductoResponseDTO productoEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        productoEjemplo = ProductoResponseDTO.builder()
                .id(1)
                .nombre("Procesador Ryzen 5 7600")
                .marca("AMD")
                .modelo("Ryzen 5 7600")
                .precio(new BigDecimal("249990"))
                .categoriaId(1)
                .estado("ACTIVO")
                .build();
    }

    @Test
    void listar_retorna200ConProductos() throws Exception {
        // ARRANGE: creamos la lista falsa
        List<ProductoResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(productoEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT: simulamos el GET y verificamos la respuesta
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Procesador Ryzen 5 7600"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(productoEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.marca").value("AMD"));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Producto no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/productos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(ProductoCreateDTO.class))).thenReturn(productoEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombre":"Procesador Ryzen 5 7600",
                                  "marca":"AMD",
                                  "modelo":"Ryzen 5 7600",
                                  "precio":249990,
                                  "categoriaId":1,
                                  "estado":"ACTIVO"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Procesador Ryzen 5 7600"));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(ProductoUpdateDTO.class))).thenReturn(productoEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombre":"Procesador Ryzen 5 7600",
                                  "marca":"AMD",
                                  "modelo":"Ryzen 5 7600",
                                  "precio":249990,
                                  "categoriaId":1,
                                  "estado":"ACTIVO"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Ryzen 5 7600"));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }

    @Test
    void listarPorCategoria_retorna200() throws Exception {
        // ARRANGE
        when(service.listarPorCategoria(1)).thenReturn(List.of(productoEjemplo));

        // ACT + ASSERT
        mockMvc.perform(get("/api/productos/categoria/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoriaId").value(1));
    }

    @Test
    void buscar_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorTexto("amd")).thenReturn(List.of(productoEjemplo));

        // ACT + ASSERT
        mockMvc.perform(get("/api/productos/buscar").param("texto", "amd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("AMD"));
    }

    @Test
    void filtrarPrecio_retorna200() throws Exception {
        // ARRANGE
        when(service.filtrarPorPrecio(new BigDecimal("100000"), new BigDecimal("300000")))
                .thenReturn(List.of(productoEjemplo));

        // ACT + ASSERT
        mockMvc.perform(get("/api/productos/precio")
                        .param("rangoMin", "100000")
                        .param("rangoMax", "300000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].precio").value(249990));
    }
}
