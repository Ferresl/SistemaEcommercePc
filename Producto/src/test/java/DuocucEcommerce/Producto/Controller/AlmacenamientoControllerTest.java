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

import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoCreateDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoResponseDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoUpdateDTO;
import DuocucEcommerce.Producto.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Service.AlmacenamientoService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class AlmacenamientoControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private AlmacenamientoService service; // servicio falso

    @InjectMocks
    private AlmacenamientoController controller;

    private AlmacenamientoResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = AlmacenamientoResponseDTO.builder()
                .id(1)
                .productoId(10)
                .tipo("SSD")
                .capacidadGb(1000)
                .interfaz("NVMe")
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE: creamos la lista falsa
        List<AlmacenamientoResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT: simulamos el GET y verificamos la respuesta
        mockMvc.perform(get("/api/almacenamientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(10));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/almacenamientos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Ficha tecnica no encontrada con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/almacenamientos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorProducto_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorProductoId(10)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/almacenamientos/producto/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(AlmacenamientoCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/almacenamientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "tipo":"SSD",
                                  "capacidadGb":1000,
                                  "interfaz":"NVMe"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(AlmacenamientoUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/almacenamientos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "tipo":"SSD",
                                  "capacidadGb":1000,
                                  "interfaz":"NVMe"
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
        mockMvc.perform(delete("/api/almacenamientos/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}
