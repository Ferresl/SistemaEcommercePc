package DuocucEcommerce.User.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioCreateDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioResponseDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioUpdateDTO;
import DuocucEcommerce.User.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones para consultar y mantener usuarios.")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    
    @Operation(summary = "Listar usuarios", description = "Obtiene todos los registros de usuarios.")
    @GetMapping 
    public ResponseEntity<List<UsuarioResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar usuario por ID", description = "Obtiene un registro de usuario usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Buscar usuario por correo", description = "Obtiene un usuario usando su correo electronico.")
    @GetMapping("/email/{email}") 
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@PathVariable String email) { 
        return ResponseEntity.ok(service.buscarPorEmail(email)); 
    }
    
    @Operation(summary = "Crear usuario", description = "Registra un nuevo recurso de usuario.")
    @PostMapping 
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un registro de usuario.")
    @PutMapping("/{id}") 
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Eliminar usuario", description = "Elimina un registro de usuario.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
