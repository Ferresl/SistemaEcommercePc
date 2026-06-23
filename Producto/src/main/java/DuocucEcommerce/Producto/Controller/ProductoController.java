package DuocucEcommerce.Producto.Controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoCreateDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoResponseDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoUpdateDTO;
import DuocucEcommerce.Producto.Service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Productos", description = "Catalogo general de productos.")
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;
    
    
    @Operation(summary = "Listar productos", description = "Obtiene todos los registros de productos.")
    @GetMapping 
    public ResponseEntity<List<ProductoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    
    @Operation(summary = "Buscar producto por ID", description = "Obtiene un registro de producto usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<ProductoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    
    @Operation(summary = "Crear producto", description = "Registra un nuevo recurso de producto.")
    @PostMapping 
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    
    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un registro de producto.")
    @PutMapping("/{id}") 
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    
    @Operation(summary = "Eliminar producto", description = "Elimina un registro de producto.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
    
    
    @Operation(summary = "Consultar productos", description = "Consulta informacion de productos.")
    @GetMapping("/categoria/{categoriaId}") 
    public ResponseEntity<List<ProductoResponseDTO>> listarPorCategoria(@PathVariable Integer categoriaId) { 
        return ResponseEntity.ok(service.listarPorCategoria(categoriaId)); 
    }
    
    
    @Operation(summary = "Consultar productos", description = "Consulta informacion de productos.")
    @GetMapping("/buscar") 
    public ResponseEntity<List<ProductoResponseDTO>> buscar(@RequestParam String texto) { 
        return ResponseEntity.ok(service.buscarPorTexto(texto)); }
    
    
    @Operation(summary = "Consultar productos", description = "Consulta informacion de productos.")
    @GetMapping("/precio") 
    public ResponseEntity<List<ProductoResponseDTO>> filtrarPrecio(@RequestParam BigDecimal rangoMin, @RequestParam BigDecimal rangoMax) { 
        return ResponseEntity.ok(service.filtrarPorPrecio(rangoMin, rangoMax)); 
    }
}
