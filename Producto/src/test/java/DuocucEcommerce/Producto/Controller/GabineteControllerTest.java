package DuocucEcommerce.Producto.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteCreateDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteResponseDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteUpdateDTO;
import DuocucEcommerce.Producto.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Service.GabineteService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class GabineteControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private GabineteService service; // servicio falso

    @InjectMocks
    private GabineteController controller;

    private GabineteResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = GabineteResponseDTO.builder()
                .id(1)
                .productoId(10)
                .formatoSoportado("ATX")
                .largoMaxGpuMm(360)
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE: creamos la lista falsa
        List<GabineteResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT: simulamos el GET y verificamos la respuesta
        mockMvc.perform(get("/api/gabinetes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(10));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/gabinetes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Ficha tecnica no encontrada con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/gabinetes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorProducto_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorProductoId(10)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/gabinetes/producto/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(GabineteCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/gabinetes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "formatoSoportado":"ATX",
                                  "largoMaxGpuMm":360
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(GabineteUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/gabinetes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "formatoSoportado":"ATX",
                                  "largoMaxGpuMm":360
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE: el servicio no hace nada
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/gabinetes/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}
