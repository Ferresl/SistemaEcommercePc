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

@RestController
@RequestMapping("/api/cpus")
@RequiredArgsConstructor
public class CPUController {
    private final CPUService service;

    @GetMapping
    public ResponseEntity<List<CPUResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<CPUResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<CPUResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @PostMapping
    public ResponseEntity<CPUResponseDTO> crear(@Valid @RequestBody CPUCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<CPUResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CPUUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}