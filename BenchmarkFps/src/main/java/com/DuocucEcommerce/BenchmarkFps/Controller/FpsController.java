package com.DuocucEcommerce.BenchmarkFps.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionRequestDTO;
import com.DuocucEcommerce.BenchmarkFps.Service.FpsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estimador FPS", description = "Calculo de FPS segun configuracion y juego.")
@RestController
@RequestMapping("/api/fps")
@RequiredArgsConstructor
public class FpsController {
    private final FpsService service;
    @Operation(summary = "Estimar FPS", description = "Calcula una estimacion de FPS para una configuracion y un juego.")
    @PostMapping("/estimar")
    public ResponseEntity<EstimacionFPSResponseDTO> estimar(@Valid @RequestBody EstimacionRequestDTO dto) { 
        return ResponseEntity.ok(service.estimar(dto)); 
    }
}
