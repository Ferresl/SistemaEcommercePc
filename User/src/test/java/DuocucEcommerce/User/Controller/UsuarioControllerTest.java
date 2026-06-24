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

import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioCreateDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioResponseDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioUpdateDTO;
import DuocucEcommerce.User.Exception.GlobalExceptionHandler;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Model.RolUsuario;
import DuocucEcommerce.User.Service.UsuarioService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class UsuarioControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private UsuarioService service; // servicio falso

    @InjectMocks
    private UsuarioController controller;

    private UsuarioResponseDTO usuarioEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        usuarioEjemplo = UsuarioResponseDTO.builder()
                .id(1)
                .nombre("Juan")
                .apellido("Ferreira")
                .email("juan@correo.cl")
                .telefono("+56912345678")
                .rol(RolUsuario.CLIENTE)
                .estado("ACTIVO")
                .build();
    }

    @Test
    void listar_retorna200ConUsuarios() throws Exception {
        // ARRANGE
        List<UsuarioResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(usuarioEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("juan@correo.cl"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(usuarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Usuario no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/usuarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorEmail_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorEmail("juan@correo.cl")).thenReturn(usuarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/usuarios/email/juan@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(UsuarioCreateDTO.class))).thenReturn(usuarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\",\"apellido\":\"Ferreira\",\"email\":\"juan@correo.cl\",\"telefono\":\"+56912345678\",\"rol\":\"CLIENTE\",\"estado\":\"ACTIVO\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("juan@correo.cl"));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(UsuarioUpdateDTO.class))).thenReturn(usuarioEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\",\"apellido\":\"Ferreira\",\"email\":\"juan@correo.cl\",\"telefono\":\"+56912345678\",\"rol\":\"CLIENTE\",\"estado\":\"ACTIVO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("CLIENTE"));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}