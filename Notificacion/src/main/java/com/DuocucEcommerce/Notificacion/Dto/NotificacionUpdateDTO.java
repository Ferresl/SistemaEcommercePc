package com.DuocucEcommerce.Notificacion.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionUpdateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    private Integer usuarioId;

    @NotBlank(message = "El campo titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El campo mensaje es obligatorio")
    private String mensaje;

    @NotBlank(message = "El campo tipo es obligatorio")
    private String tipo;

    @NotNull(message = "El campo leida es obligatorio")
    private Boolean leida;

}
