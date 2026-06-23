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

import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteCreateDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteResponseDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteUpdateDTO;
import DuocucEcommerce.Producto.Service.GabineteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gabinetes", description = "Datos tecnicos de gabinetes.")
@RestController
@RequestMapping("/api/gabinetes")
@RequiredArgsConstructor
public class GabineteController {
    private final GabineteService service;

    @Operation(summary = "Listar gabinetes", description = "Obtiene todos los registros de gabinetes.")
    @GetMapping
    public ResponseEntity<List<GabineteResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @Operation(summary = "Buscar gabinete por ID", description = "Obtiene un registro de gabinete usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<GabineteResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @Operation(summary = "Buscar gabinetes por producto", description = "Obtiene gabinetes asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<GabineteResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear gabinete", description = "Registra un nuevo recurso de gabinete.")
    @PostMapping
    public ResponseEntity<GabineteResponseDTO> crear(@Valid @RequestBody GabineteCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @Operation(summary = "Actualizar gabinete", description = "Actualiza los datos de un registro de gabinete.")
    @PutMapping("/{id}")
    public ResponseEntity<GabineteResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody GabineteUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar gabinete", description = "Elimina un registro de gabinete.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
