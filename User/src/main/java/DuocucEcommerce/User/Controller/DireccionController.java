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

import DuocucEcommerce.User.Dto.DireccionDto.DireccionCreateDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionResponseDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionUpdateDTO;
import DuocucEcommerce.User.Service.DireccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Direcciones", description = "Direcciones registradas para cada usuario.")
@RestController
@RequestMapping("/api/direcciones")
@RequiredArgsConstructor
public class DireccionController {
    private final DireccionService service;
    
    @Operation(summary = "Listar direcciones", description = "Obtiene todos los registros de direcciones.")
    @GetMapping 
    public ResponseEntity<List<DireccionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar direccion por ID", description = "Obtiene un registro de direccion usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<DireccionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Listar direcciones por usuario", description = "Obtiene los registros de direcciones asociados a un usuario.")
    @GetMapping("/usuario/{usuarioId}") 
    public ResponseEntity<List<DireccionResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
    @Operation(summary = "Crear direccion", description = "Registra un nuevo recurso de direccion.")
    @PostMapping 
    public ResponseEntity<DireccionResponseDTO> crear(@Valid @RequestBody DireccionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar direccion", description = "Actualiza los datos de un registro de direccion.")
    @PutMapping("/{id}") 
    public ResponseEntity<DireccionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DireccionUpdateDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Eliminar direccion", description = "Elimina un registro de direccion.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
