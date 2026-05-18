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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsuarioAuthController {
    private final UsuarioAuthService service;

    @PostMapping("/register")
    public ResponseEntity<UsuarioAuthResponseDTO> registrar(@Valid @RequestBody RegistroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioAuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @GetMapping("/validar-token")
    public ResponseEntity<Boolean> validarToken(@RequestParam String token) {
        return ResponseEntity.ok(service.validarToken(token));
    }
}
