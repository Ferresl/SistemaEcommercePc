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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RAM", description = "Datos tecnicos de memorias RAM.")
@RestController
@RequestMapping("/api/rams")
@RequiredArgsConstructor
public class RAMController {
    private final RAMService service;

    @Operation(summary = "Listar ram", description = "Obtiene todos los registros de ram.")
    @GetMapping
    public ResponseEntity<List<RAMResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
   
   
    @Operation(summary = "Buscar RAM por ID", description = "Obtiene un registro de RAM usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<RAMResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
   
   
    @Operation(summary = "Buscar ram por producto", description = "Obtiene ram asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<RAMResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear RAM", description = "Registra un nuevo recurso de RAM.")
    @PostMapping
    public ResponseEntity<RAMResponseDTO> crear(@Valid @RequestBody RAMCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @Operation(summary = "Actualizar RAM", description = "Actualiza los datos de un registro de RAM.")
    @PutMapping("/{id}")
    public ResponseEntity<RAMResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody RAMUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar RAM", description = "Elimina un registro de RAM.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
