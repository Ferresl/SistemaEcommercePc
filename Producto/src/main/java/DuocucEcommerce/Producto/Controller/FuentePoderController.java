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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Fuentes de poder", description = "Datos tecnicos de fuentes de poder.")
@RestController
@RequestMapping("/api/fuentes")
@RequiredArgsConstructor
public class FuentePoderController {
    private final FuentePoderService service;

    @Operation(summary = "Listar fuentes de poder", description = "Obtiene todos los registros de fuentes de poder.")
    @GetMapping
    public ResponseEntity<List<FuentePoderResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    @Operation(summary = "Buscar fuente de poder por ID", description = "Obtiene un registro de fuente de poder usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<FuentePoderResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    
    
    @Operation(summary = "Buscar fuentes de poder por producto", description = "Obtiene fuentes de poder asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<FuentePoderResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    @PostMapping
    
    
    public ResponseEntity<FuentePoderResponseDTO> crear(@Valid @RequestBody FuentePoderCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @Operation(summary = "Actualizar fuente de poder", description = "Actualiza los datos de un registro de fuente de poder.")
    @PutMapping("/{id}")
    
    
    public ResponseEntity<FuentePoderResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody FuentePoderUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @Operation(summary = "Eliminar fuente de poder", description = "Elimina un registro de fuente de poder.")
    @DeleteMapping("/{id}")
    
    
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
