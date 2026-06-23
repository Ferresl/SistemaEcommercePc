package DuocucEcommerce.Producto.Controller;

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

import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoCreateDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoResponseDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoUpdateDTO;
import DuocucEcommerce.Producto.Service.AlmacenamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/almacenamientos")
@RequiredArgsConstructor
public class AlmacenamientoController {
    private final AlmacenamientoService service;

    @GetMapping
    public ResponseEntity<List<AlmacenamientoResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<AlmacenamientoResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @PostMapping
    public ResponseEntity<AlmacenamientoResponseDTO> crear(@Valid @RequestBody AlmacenamientoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody AlmacenamientoUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}