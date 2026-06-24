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

import DuocucEcommerce.Producto.Dto.RAMDTO.RAMCreateDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMResponseDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMUpdateDTO;
import DuocucEcommerce.Producto.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Service.RAMService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class RAMControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private RAMService service; // servicio falso

    @InjectMocks
    private RAMController controller;

    private RAMResponseDTO ejemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        ejemplo = RAMResponseDTO.builder()
                .id(1)
                .productoId(10)
                .tipoMemoria("DDR5")
                .capacidadGb(32)
                .frecuenciaMhz(6000)
                .build();
    }

    @Test
    void listar_retorna200ConRegistros() throws Exception {
        // ARRANGE: creamos la lista falsa
        List<RAMResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT: simulamos el GET y verificamos la respuesta
        mockMvc.perform(get("/api/rams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(10));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/rams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Ficha tecnica no encontrada con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/rams/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorProducto_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorProductoId(10)).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/rams/producto/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(RAMCreateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/rams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "tipoMemoria":"DDR5",
                                  "capacidadGb":32,
                                  "frecuenciaMhz":6000
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(10));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(RAMUpdateDTO.class))).thenReturn(ejemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/rams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productoId":10,
                                  "tipoMemoria":"DDR5",
                                  "capacidadGb":32,
                                  "frecuenciaMhz":6000
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
        mockMvc.perform(delete("/api/rams/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}
