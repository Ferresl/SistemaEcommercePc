package DuocucEcommerce.Auth_User.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DuocucEcommerce.Auth_User.Model.RolUsuario;
import DuocucEcommerce.Auth_User.Model.UsuarioAuth;

public class JwtServiceTest {

    private JwtService service;
    private UsuarioAuth usuarioAuth;

    @BeforeEach
    void setUp() throws Exception {
        service = new JwtService();
        setField("secret", Base64.getEncoder().encodeToString("clave-super-secreta-para-pruebas-unitarias".getBytes()));
        setField("expiration", 3600000);

        usuarioAuth = UsuarioAuth.builder()
                .usuarioId(10)
                .email("juan@correo.cl")
                .rol(RolUsuario.CLIENTE)
                .build();
    }

    @Test
    void generarToken_retornaTokenValido() {
        // ACT
        String token = service.generarToken(usuarioAuth);

        // ASSERT
        assertNotNull(token);
        assertTrue(service.validarToken(token));
    }

    @Test
    void validarToken_tokenInvalido_retornaFalse() {
        // ACT
        Boolean resultado = service.validarToken("token-invalido");

        // ASSERT
        assertFalse(resultado);
    }

    private void setField(String fieldName, Object value) throws Exception {
        Field field = JwtService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(service, value);
    }
}