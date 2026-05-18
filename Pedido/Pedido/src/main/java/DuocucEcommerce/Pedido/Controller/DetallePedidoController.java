package DuocucEcommerce.Pedido.Controller;


import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoUpdateDTO;
import DuocucEcommerce.Pedido.Service.DetallePedidoService;



@RestController
@RequestMapping("/api/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {
    private final DetallePedidoService service;

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar()); 
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO> crear(@Valid @RequestBody DetallePedidoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DetallePedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}