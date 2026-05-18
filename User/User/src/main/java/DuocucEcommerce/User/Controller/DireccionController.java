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

@RestController
@RequestMapping("/api/direcciones")
@RequiredArgsConstructor
public class DireccionController {
    private final DireccionService service;
    
    @GetMapping public ResponseEntity<List<DireccionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @GetMapping("/{id}") public ResponseEntity<DireccionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @GetMapping("/usuario/{usuarioId}") public ResponseEntity<List<DireccionResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
    @PostMapping public ResponseEntity<DireccionResponseDTO> crear(@Valid @RequestBody DireccionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @PutMapping("/{id}") public ResponseEntity<DireccionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DireccionUpdateDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
