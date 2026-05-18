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

@RestController
@RequestMapping("/api/gpus")
@RequiredArgsConstructor
public class GPUController {
    private final GPUService service;

    @GetMapping
    public ResponseEntity<List<GPUResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<GPUResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<GPUResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @PostMapping
    public ResponseEntity<GPUResponseDTO> crear(@Valid @RequestBody GPUCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<GPUResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody GPUUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}