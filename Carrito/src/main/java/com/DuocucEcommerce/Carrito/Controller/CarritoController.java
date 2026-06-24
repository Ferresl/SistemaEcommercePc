package com.DuocucEcommerce.Carrito.Controller;


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

import com.DuocucEcommerce.Carrito.Dto.ActualizarCantidadItemDTO;
import com.DuocucEcommerce.Carrito.Dto.AgregarItemCarritoDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Carritos", description = "Carritos de compra de los usuarios.")
@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
public class CarritoController {
    private final CarritoService service;
    
    @Operation(summary = "Listar carritos", description = "Obtiene todos los registros de carritos.")
    @GetMapping 
    public ResponseEntity<List<CarritoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar carrito por ID", description = "Obtiene un registro de carrito usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<CarritoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Buscar carritos por usuario", description = "Obtiene carritos asociados a un usuario.")
    @GetMapping("/usuario/{usuarioId}") 
    public ResponseEntity<CarritoResponseDTO> buscarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId)); 
    }
    
    @Operation(summary = "Crear carrito", description = "Registra un nuevo recurso de carrito.")
    @PostMapping 
    public ResponseEntity<CarritoResponseDTO> crear(@Valid @RequestBody CarritoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar carrito", description = "Actualiza los datos de un registro de carrito.")
    @PutMapping("/{id}") 
    public ResponseEntity<CarritoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CarritoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Agregar item", description = "Agrega un producto al registro principal.")
    @PostMapping("/{carritoId}/items") 
    public ResponseEntity<CarritoResponseDTO> agregarItem(@PathVariable Integer carritoId, @Valid @RequestBody AgregarItemCarritoDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarItem(carritoId, dto)); 
    }
    
    @Operation(summary = "Actualizar cantidad", description = "Actualiza la cantidad de un item.")
    @PutMapping("/items/{itemId}") 
    public ResponseEntity<CarritoResponseDTO> actualizarCantidad(@PathVariable Integer itemId, @Valid @RequestBody ActualizarCantidadItemDTO dto) { 
        return ResponseEntity.ok(service.actualizarCantidad(itemId, dto)); 
    }
    
    @Operation(summary = "Eliminar item", description = "Quita un item del registro principal.")
    @DeleteMapping("/items/{itemId}") 
    public ResponseEntity<Void> eliminarItem(@PathVariable Integer itemId) { 
        service.eliminarItem(itemId); 
        return ResponseEntity.noContent().build(); 
    }
    
    @Operation(summary = "Vaciar carrito", description = "Elimina todos los items de un carrito.")
    @DeleteMapping("/{carritoId}/vaciar") 
    public ResponseEntity<Void> vaciar(@PathVariable Integer carritoId) { 
        service.vaciar(carritoId); 
        return ResponseEntity.noContent().build(); 
    }
    
    @Operation(summary = "Eliminar carrito", description = "Elimina un registro de carrito.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
