package DuocucEcommerce.Pedido.Controller;

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

import DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO.EstadoPedidoUpdateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoResponseDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoUpdateDTO;
import DuocucEcommerce.Pedido.Service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Pedidos realizados por los usuarios.")
@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @Operation(summary = "Listar pedidos", description = "Obtiene todos los registros de pedidos.")
    @GetMapping 
    public ResponseEntity<List<PedidoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar pedido por ID", description = "Obtiene un registro de pedido usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Listar pedidos por usuario", description = "Obtiene los registros de pedidos asociados a un usuario.")
    @GetMapping("/usuario/{usuarioId}") 
    public ResponseEntity<List<PedidoResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
     @Operation(summary = "Crear pedido", description = "Registra un nuevo recurso de pedido.")
     @PostMapping 
     public ResponseEntity<PedidoResponseDTO> crear(@Valid @RequestBody PedidoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar pedido", description = "Actualiza los datos de un registro de pedido.")
    @PutMapping("/{id}") 
    public ResponseEntity<PedidoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Cambiar estado del pedido", description = "Actualiza el estado actual de un pedido.")
    @PutMapping("/{id}/estado") 
    public ResponseEntity<PedidoResponseDTO> cambiarEstado(@PathVariable Integer id, @Valid @RequestBody EstadoPedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.cambiarEstado(id, dto)); 
    }
    
    @Operation(summary = "Eliminar pedido", description = "Elimina un registro de pedido.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
