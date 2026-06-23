package com.DuocucEcommerce.Notificacion.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear notificacion.")
public class NotificacionCreateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @NotBlank(message = "El campo titulo es obligatorio")
    @Schema(description = "Titulo de la notificacion.", example = "Pedido confirmado")
    private String titulo;

    @NotBlank(message = "El campo mensaje es obligatorio")
    @Schema(description = "Contenido del mensaje de la notificacion.", example = "Tu pedido fue confirmado")
    private String mensaje;

    @NotBlank(message = "El campo tipo es obligatorio")
    @Schema(description = "Tipo de notificacion enviada.", example = "PEDIDO")
    private String tipo;

    @NotNull(message = "El campo leida es obligatorio")
    @Schema(description = "Indica si la notificacion ya fue leida.", example = "false")
    private Boolean leida;

}
