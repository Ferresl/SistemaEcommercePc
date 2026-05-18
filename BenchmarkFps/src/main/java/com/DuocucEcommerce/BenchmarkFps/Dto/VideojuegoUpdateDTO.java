package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String nombre;

}
