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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Almacenamientos", description = "Datos tecnicos de discos y unidades de almacenamiento.")
@RestController
@RequestMapping("/api/almacenamientos")
@RequiredArgsConstructor
public class AlmacenamientoController {
    private final AlmacenamientoService service;

    @Operation(summary = "Listar almacenamientos", description = "Obtiene todos los registros de almacenamientos.")
    @GetMapping
    public ResponseEntity<List<AlmacenamientoResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @Operation(summary = "Buscar almacenamiento por ID", description = "Obtiene un registro de almacenamiento usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @Operation(summary = "Buscar almacenamientos por producto", description = "Obtiene almacenamientos asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<AlmacenamientoResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear almacenamiento", description = "Registra un nuevo recurso de almacenamiento.")
    @PostMapping
    public ResponseEntity<AlmacenamientoResponseDTO> crear(@Valid @RequestBody AlmacenamientoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    
    @Operation(summary = "Actualizar almacenamiento", description = "Actualiza los datos de un registro de almacenamiento.")
    @PutMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody AlmacenamientoUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar almacenamiento", description = "Elimina un registro de almacenamiento.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
