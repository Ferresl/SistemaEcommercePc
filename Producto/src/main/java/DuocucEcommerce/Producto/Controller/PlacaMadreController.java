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
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreCreateDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreResponseDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreUpdateDTO;
import DuocucEcommerce.Producto.Service.PlacaMadreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/placas-madre")
@RequiredArgsConstructor
public class PlacaMadreController {
    private final PlacaMadreService service;

    @GetMapping
    public ResponseEntity<List<PlacaMadreResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<PlacaMadreResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
   
   
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<PlacaMadreResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @PostMapping
    public ResponseEntity<PlacaMadreResponseDTO> crear(@Valid @RequestBody PlacaMadreCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<PlacaMadreResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PlacaMadreUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}