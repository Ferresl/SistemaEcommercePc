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

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService service;
    @GetMapping public ResponseEntity<List<NotificacionResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}") public ResponseEntity<NotificacionResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @GetMapping("/usuario/{usuarioId}") public ResponseEntity<List<NotificacionResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); }
    @PostMapping public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}") public ResponseEntity<NotificacionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody NotificacionUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @PutMapping("/{id}/leer") public ResponseEntity<NotificacionResponseDTO> marcarLeida(@PathVariable Integer id) { return ResponseEntity.ok(service.marcarLeida(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
