package com.DuocucEcommerce.Notificacion.Controller;

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

import com.DuocucEcommerce.Notificacion.Dto.NotificacionCreateDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionResponseDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionUpdateDTO;
import com.DuocucEcommerce.Notificacion.Service.NotificacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notificaciones", description = "Mensajes y avisos enviados a usuarios.")
@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService service;
    
    @Operation(summary = "Listar notificaciones", description = "Obtiene todos los registros de notificaciones.")
    @GetMapping 
    public ResponseEntity<List<NotificacionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar notificacion por ID", description = "Obtiene un registro de notificacion usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<NotificacionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Listar notificaciones por usuario", description = "Obtiene los registros de notificaciones asociados a un usuario.")
    @GetMapping("/usuario/{usuarioId}") 
    public ResponseEntity<List<NotificacionResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
    @Operation(summary = "Crear notificacion", description = "Registra un nuevo recurso de notificacion.")
    @PostMapping 
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar notificacion", description = "Actualiza los datos de un registro de notificacion.")
    @PutMapping("/{id}") 
    public ResponseEntity<NotificacionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody NotificacionUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Marcar notificacion como leida", description = "Marca una notificacion como revisada.")
    @PutMapping("/{id}/leer") 
    public ResponseEntity<NotificacionResponseDTO> marcarLeida(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.marcarLeida(id)); 
    }
    
    @Operation(summary = "Eliminar notificacion", description = "Elimina un registro de notificacion.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
