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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Detalles de pedido", description = "Productos incluidos en un pedido.")
@RestController
@RequestMapping("/api/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {
    private final DetallePedidoService service;

    @Operation(summary = "Listar detalles de pedido", description = "Obtiene todos los registros de detalles de pedido.")
    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar()); 
    }
    @Operation(summary = "Buscar detalle de pedido por ID", description = "Obtiene un registro de detalle de pedido usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @Operation(summary = "Crear detalle de pedido", description = "Registra un nuevo recurso de detalle de pedido.")
    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO> crear(@Valid @RequestBody DetallePedidoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @Operation(summary = "Actualizar detalle de pedido", description = "Actualiza los datos de un registro de detalle de pedido.")
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DetallePedidoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @Operation(summary = "Eliminar detalle de pedido", description = "Elimina un registro de detalle de pedido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
