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

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @GetMapping 
    public ResponseEntity<List<PedidoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @GetMapping("/usuario/{usuarioId}") 
    public ResponseEntity<List<PedidoResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
     @PostMapping 
     public ResponseEntity<PedidoResponseDTO> crear(@Valid @RequestBody PedidoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @PutMapping("/{id}") 
    public ResponseEntity<PedidoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @PutMapping("/{id}/estado") 
    public ResponseEntity<PedidoResponseDTO> cambiarEstado(@PathVariable Integer id, @Valid @RequestBody EstadoPedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.cambiarEstado(id, dto)); 
    }
    
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}