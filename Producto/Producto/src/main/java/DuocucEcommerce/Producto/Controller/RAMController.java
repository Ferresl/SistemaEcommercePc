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

import DuocucEcommerce.Producto.Dto.RAMDTO.RAMCreateDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMResponseDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMUpdateDTO;
import DuocucEcommerce.Producto.Service.RAMService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rams")
@RequiredArgsConstructor
public class RAMController {
    private final RAMService service;

    @GetMapping
    public ResponseEntity<List<RAMResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
   
   
    @GetMapping("/{id}")
    public ResponseEntity<RAMResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
   
   
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<RAMResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @PostMapping
    public ResponseEntity<RAMResponseDTO> crear(@Valid @RequestBody RAMCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<RAMResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody RAMUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}