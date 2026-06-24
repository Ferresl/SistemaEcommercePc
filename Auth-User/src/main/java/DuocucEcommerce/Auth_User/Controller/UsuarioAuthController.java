package DuocucEcommerce.Auth_User.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DuocucEcommerce.Auth_User.Dto.LoginRequestDTO;
import DuocucEcommerce.Auth_User.Dto.RegistroRequestDTO;
import DuocucEcommerce.Auth_User.Dto.UsuarioAuthResponseDTO;
import DuocucEcommerce.Auth_User.Service.UsuarioAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticacion", description = "Registro, login y validacion de tokens.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsuarioAuthController {
    private final UsuarioAuthService service;

    @Operation(summary = "Registrar usuario", description = "Crea la cuenta de autenticacion y el usuario asociado.")
    @PostMapping("/register")
    public ResponseEntity<UsuarioAuthResponseDTO> registrar(@Valid @RequestBody RegistroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Iniciar sesion", description = "Valida credenciales y devuelve la respuesta de autenticacion.")
    @PostMapping("/login")
    public ResponseEntity<UsuarioAuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @Operation(summary = "Validar token", description = "Revisa si el token JWT recibido sigue siendo valido.")
    @GetMapping("/validar-token")
    public ResponseEntity<Boolean> validarToken(@RequestParam String token) {
        return ResponseEntity.ok(service.validarToken(token));
    }
}
