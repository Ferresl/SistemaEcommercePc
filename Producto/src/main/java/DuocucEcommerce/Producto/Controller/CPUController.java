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

import DuocucEcommerce.Producto.Dto.CPUDTO.CPUCreateDTO;
import DuocucEcommerce.Producto.Dto.CPUDTO.CPUResponseDTO;
import DuocucEcommerce.Producto.Dto.CPUDTO.CPUUpdateDTO;
import DuocucEcommerce.Producto.Service.CPUService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CPUs", description = "Datos tecnicos de procesadores.")
@RestController
@RequestMapping("/api/cpus")
@RequiredArgsConstructor
public class CPUController {
    private final CPUService service;

    @Operation(summary = "Listar cpus", description = "Obtiene todos los registros de cpus.")
    @GetMapping
    public ResponseEntity<List<CPUResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @Operation(summary = "Buscar CPU por ID", description = "Obtiene un registro de CPU usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<CPUResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @Operation(summary = "Buscar cpus por producto", description = "Obtiene cpus asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<CPUResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear CPU", description = "Registra un nuevo recurso de CPU.")
    @PostMapping
    public ResponseEntity<CPUResponseDTO> crear(@Valid @RequestBody CPUCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @Operation(summary = "Actualizar CPU", description = "Actualiza los datos de un registro de CPU.")
    @PutMapping("/{id}")
    public ResponseEntity<CPUResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CPUUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar CPU", description = "Elimina un registro de CPU.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
