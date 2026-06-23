package com.DuocucEcommerce.ConfiguracionPc.Controller;

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

import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCUpdateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Service.ConfiguracionPCService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Configuraciones de PC", description = "Configuraciones armadas por los usuarios.")
@RestController
@RequestMapping("/api/configuraciones")
@RequiredArgsConstructor
public class ConfiguracionPCController {
    private final ConfiguracionPCService service;
    @Operation(summary = "Listar configuraciones de pc", description = "Obtiene todos los registros de configuraciones de pc.")
    @GetMapping public ResponseEntity<List<ConfiguracionPCResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Buscar configuracion de PC por ID", description = "Obtiene un registro de configuracion de PC usando su identificador.")
    @GetMapping("/{id}") public ResponseEntity<ConfiguracionPCResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @Operation(summary = "Listar configuraciones de pc por usuario", description = "Obtiene los registros de configuraciones de pc asociados a un usuario.")
    @GetMapping("/usuario/{usuarioId}") public ResponseEntity<List<ConfiguracionPCResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); }
    @Operation(summary = "Crear configuracion de PC", description = "Registra un nuevo recurso de configuracion de PC.")
    @PostMapping public ResponseEntity<ConfiguracionPCResponseDTO> crear(@Valid @RequestBody ConfiguracionPCCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @Operation(summary = "Actualizar configuracion de PC", description = "Actualiza los datos de un registro de configuracion de PC.")
    @PutMapping("/{id}") public ResponseEntity<ConfiguracionPCResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConfiguracionPCUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @Operation(summary = "Evaluar compatibilidad", description = "Evalua la compatibilidad de una configuracion de PC.")
    @PostMapping("/{id}/evaluar") public ResponseEntity<ConfiguracionPCResponseDTO> evaluar(@PathVariable Integer id) { return ResponseEntity.ok(service.evaluar(id)); }
    @Operation(summary = "Eliminar configuracion de PC", description = "Elimina un registro de configuracion de PC.")
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
