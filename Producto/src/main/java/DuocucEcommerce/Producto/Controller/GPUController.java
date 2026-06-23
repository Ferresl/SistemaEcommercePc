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

import DuocucEcommerce.Producto.Dto.GPUDTO.GPUCreateDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUResponseDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUUpdateDTO;
import DuocucEcommerce.Producto.Service.GPUService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "GPUs", description = "Datos tecnicos de tarjetas graficas.")
@RestController
@RequestMapping("/api/gpus")
@RequiredArgsConstructor
public class GPUController {
    private final GPUService service;

    @Operation(summary = "Listar gpus", description = "Obtiene todos los registros de gpus.")
    @GetMapping
    public ResponseEntity<List<GPUResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @Operation(summary = "Buscar GPU por ID", description = "Obtiene un registro de GPU usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<GPUResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @Operation(summary = "Buscar gpus por producto", description = "Obtiene gpus asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<GPUResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear GPU", description = "Registra un nuevo recurso de GPU.")
    @PostMapping
    public ResponseEntity<GPUResponseDTO> crear(@Valid @RequestBody GPUCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @Operation(summary = "Actualizar GPU", description = "Actualiza los datos de un registro de GPU.")
    @PutMapping("/{id}")
    public ResponseEntity<GPUResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody GPUUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar GPU", description = "Elimina un registro de GPU.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
