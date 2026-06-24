package DuocucEcommerce.Auth_User.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import DuocucEcommerce.Auth_User.Dto.LoginRequestDTO;
import DuocucEcommerce.Auth_User.Dto.RegistroRequestDTO;
import DuocucEcommerce.Auth_User.Dto.UsuarioAuthResponseDTO;
import DuocucEcommerce.Auth_User.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Auth_User.Model.RolUsuario;
import DuocucEcommerce.Auth_User.Service.UsuarioAuthService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class UsuarioAuthControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private UsuarioAuthService service; // servicio falso

    @InjectMocks
    private UsuarioAuthController controller;

    private UsuarioAuthResponseDTO respuesta;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        respuesta = UsuarioAuthResponseDTO.builder()
                .usuarioId(10)
                .email("juan@correo.cl")
                .rol(RolUsuario.CLIENTE)
                .token("token")
                .build();
    }

    @Test
    void registrar_retorna201() throws Exception {
        // ARRANGE
        when(service.registrar(any(RegistroRequestDTO.class))).thenReturn(respuesta);

        // ACT + ASSERT
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\",\"apellido\":\"Ferreira\",\"email\":\"juan@correo.cl\",\"password\":\"Password123\",\"confirmarPassword\":\"Password123\",\"rol\":\"CLIENTE\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    void login_retorna200() throws Exception {
        // ARRANGE
        when(service.login(any(LoginRequestDTO.class))).thenReturn(respuesta);

        // ACT + ASSERT
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"juan@correo.cl\",\"password\":\"Password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("juan@correo.cl"));
    }

    @Test
    void validarToken_retorna200() throws Exception {
        // ARRANGE
        when(service.validarToken("token")).thenReturn(true);

        // ACT + ASSERT
        mockMvc.perform(get("/api/auth/validar-token").param("token", "token"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}