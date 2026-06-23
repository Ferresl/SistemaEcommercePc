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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Placas madre", description = "Datos tecnicos de placas madre.")
@RestController
@RequestMapping("/api/placas-madre")
@RequiredArgsConstructor
public class PlacaMadreController {
    private final PlacaMadreService service;

    @Operation(summary = "Listar placas madre", description = "Obtiene todos los registros de placas madre.")
    @GetMapping
    public ResponseEntity<List<PlacaMadreResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    
    
    @Operation(summary = "Buscar placa madre por ID", description = "Obtiene un registro de placa madre usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<PlacaMadreResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
   
   
    @Operation(summary = "Buscar placas madre por producto", description = "Obtiene placas madre asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<PlacaMadreResponseDTO> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.buscarPorProductoId(productoId));
    }    
    
    @Operation(summary = "Crear placa madre", description = "Registra un nuevo recurso de placa madre.")
    @PostMapping
    public ResponseEntity<PlacaMadreResponseDTO> crear(@Valid @RequestBody PlacaMadreCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    
    
    @Operation(summary = "Actualizar placa madre", description = "Actualiza los datos de un registro de placa madre.")
    @PutMapping("/{id}")
    public ResponseEntity<PlacaMadreResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PlacaMadreUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    
    
    @Operation(summary = "Eliminar placa madre", description = "Elimina un registro de placa madre.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
