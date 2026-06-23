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

import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderCreateDTO;
import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderResponseDTO;
import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderUpdateDTO;
import DuocucEcommerce.Producto.Service.FuentePoderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/fuentes")
@RequiredArgsConstructor
public class FuentePoderController {
    private final FuentePoderService service;

    @GetMapping
    public ResponseEntity<List<FuentePoderResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    @GetMapping("/{id}")
    public ResponseEntity<FuentePoderResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<FuentePoderResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    @PostMapping
    
    
    public ResponseEntity<FuentePoderResponseDTO> crear(@Valid @RequestBody FuentePoderCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}")
    
    
    public ResponseEntity<FuentePoderResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody FuentePoderUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @DeleteMapping("/{id}")
    
    
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
