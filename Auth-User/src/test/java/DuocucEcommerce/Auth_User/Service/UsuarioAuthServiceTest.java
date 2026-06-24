package DuocucEcommerce.Auth_User.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import DuocucEcommerce.Auth_User.Client.UsuarioClient;
import DuocucEcommerce.Auth_User.Client.UsuarioCreateRequestDTO;
import DuocucEcommerce.Auth_User.Client.UsuarioResponseDTO;
import DuocucEcommerce.Auth_User.Dto.LoginRequestDTO;
import DuocucEcommerce.Auth_User.Dto.RegistroRequestDTO;
import DuocucEcommerce.Auth_User.Dto.UsuarioAuthResponseDTO;
import DuocucEcommerce.Auth_User.Exception.BadRequestException;
import DuocucEcommerce.Auth_User.Model.RolUsuario;
import DuocucEcommerce.Auth_User.Model.UsuarioAuth;
import DuocucEcommerce.Auth_User.Repository.UsuarioAuthRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class UsuarioAuthServiceTest {

    @Mock
    private UsuarioAuthRepository repository; // repositorio simulado

    @Mock
    private BCryptPasswordEncoder passwordEncoder; // encoder simulado

    @Mock
    private JwtService jwtService; // jwt simulado

    @Mock
    private UsuarioClient usuarioClient; // cliente simulado

    @InjectMocks
    private UsuarioAuthService service; // servicio real con mocks inyectados

    private RegistroRequestDTO registroDTO;
    private LoginRequestDTO loginDTO;
    private UsuarioAuth authEjemplo;

    @BeforeEach
    void setUp() {
        registroDTO = RegistroRequestDTO.builder()
                .nombre("Juan")
                .apellido("Ferreira")
                .email("juan@correo.cl")
                .password("Password123")
                .confirmarPassword("Password123")
                .rol(RolUsuario.CLIENTE)
                .build();

        loginDTO = LoginRequestDTO.builder()
                .email("juan@correo.cl")
                .password("Password123")
                .build();

        authEjemplo = UsuarioAuth.builder()
                .id(1)
                .usuarioId(10)
                .email("juan@correo.cl")
                .passwordHash("hash")
                .rol(RolUsuario.CLIENTE)
                .estado("ACTIVO")
                .build();
    }

    @Test
    void registrar_retornaUsuarioAuth() {
        // ARRANGE
        when(repository.existsByEmail("juan@correo.cl")).thenReturn(false);
        when(usuarioClient.crearUsuario(any(UsuarioCreateRequestDTO.class)))
                .thenReturn(new UsuarioResponseDTO(10, "Juan", "Ferreira", "juan@correo.cl", "", RolUsuario.CLIENTE, "ACTIVO"));
        when(passwordEncoder.encode("Password123")).thenReturn("hash");
        when(repository.save(any(UsuarioAuth.class))).thenReturn(authEjemplo);
        when(passwordEncoder.matches("Password123", "hash")).thenReturn(true);
        when(jwtService.generarToken(authEjemplo)).thenReturn("token");

        // ACT
        UsuarioAuthResponseDTO resultado = service.registrar(registroDTO);

        // ASSERT
        assertEquals(10, resultado.getUsuarioId());
        assertEquals("token", resultado.getToken());
        verify(repository, times(1)).save(any(UsuarioAuth.class));
    }

    @Test
    void registrar_passwordsNoCoinciden_lanzaBadRequest() {
        // ARRANGE
        registroDTO.setConfirmarPassword("OtraPassword");

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.registrar(registroDTO);
        });

        assertEquals("Las passwords no coinciden", ex.getMessage());
    }

    @Test
    void registrar_emailDuplicado_lanzaBadRequest() {
        // ARRANGE
        when(repository.existsByEmail("juan@correo.cl")).thenReturn(true);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.registrar(registroDTO);
        });

        assertEquals("El email ya esta registrado", ex.getMessage());
    }

    @Test
    void login_credencialesValidas_retornaToken() {
        // ARRANGE
        when(repository.findByEmail("juan@correo.cl")).thenReturn(Optional.of(authEjemplo));
        when(passwordEncoder.matches("Password123", "hash")).thenReturn(true);
        when(jwtService.generarToken(authEjemplo)).thenReturn("token");

        // ACT
        UsuarioAuthResponseDTO resultado = service.login(loginDTO);

        // ASSERT
        assertEquals("juan@correo.cl", resultado.getEmail());
        assertEquals("token", resultado.getToken());
    }

    @Test
    void login_credencialesInvalidas_lanzaBadRequest() {
        // ARRANGE
        when(repository.findByEmail("juan@correo.cl")).thenReturn(Optional.of(authEjemplo));
        when(passwordEncoder.matches("Password123", "hash")).thenReturn(false);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.login(loginDTO);
        });

        assertEquals("Credenciales invalidas", ex.getMessage());
    }

    @Test
    void validarToken_retornaResultadoJwtService() {
        // ARRANGE
        when(jwtService.validarToken("token")).thenReturn(true);

        // ACT
        Boolean resultado = service.validarToken("token");

        // ASSERT
        assertTrue(resultado);
    }
}