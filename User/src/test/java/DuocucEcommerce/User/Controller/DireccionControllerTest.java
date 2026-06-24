package DuocucEcommerce.User.Controller;

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

import DuocucEcommerce.User.Dto.DireccionDto.DireccionCreateDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionResponseDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionUpdateDTO;
import DuocucEcommerce.User.Exception.GlobalExceptionHandler;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Service.DireccionService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class DireccionControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private DireccionService service; // servicio falso

    @InjectMocks
    private DireccionController controller;

    private DireccionResponseDTO direccionEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        direccionEjemplo = DireccionResponseDTO.builder()
                .id(1)
                .usuarioId(10)
                .region("Metropolitana")
                .comuna("Santiago")
                .calle("Av. Providencia")
                .numero("1234")
                .departamento("Depto 301")
                .referencia("Frente al metro")
                .principal(true)
                .build();
    }

    @Test
    void listar_retorna200ConDirecciones() throws Exception {
        // ARRANGE
        List<DireccionResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(direccionEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comuna").value("Santiago"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(direccionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/direcciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Direccion no encontrada con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/direcciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarPorUsuario_retorna200() throws Exception {
        // ARRANGE
        when(service.listarPorUsuario(10)).thenReturn(List.of(direccionEjemplo));

        // ACT + ASSERT
        mockMvc.perform(get("/api/direcciones/usuario/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(10));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(DireccionCreateDTO.class))).thenReturn(direccionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/direcciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuarioId\":10,\"region\":\"Metropolitana\",\"comuna\":\"Santiago\",\"calle\":\"Av. Providencia\",\"numero\":\"1234\",\"departamento\":\"Depto 301\",\"referencia\":\"Frente al metro\",\"principal\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.calle").value("Av. Providencia"));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(DireccionUpdateDTO.class))).thenReturn(direccionEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/direcciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuarioId\":10,\"region\":\"Metropolitana\",\"comuna\":\"Santiago\",\"calle\":\"Av. Providencia\",\"numero\":\"1234\",\"departamento\":\"Depto 301\",\"referencia\":\"Frente al metro\",\"principal\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.principal").value(true));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/direcciones/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}