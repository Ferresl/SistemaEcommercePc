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
@Schema(description = "Datos permitidos para actualizar notificacion.")
public class NotificacionUpdateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @NotBlank(message = "El campo titulo es obligatorio")
    @Schema(description = "Titulo de la notificacion.", example = "Pedido confirmado")
    private String titulo;

    @NotBlank(message = "El campo mensaje es obligatorio")
    @Schema(description = "Mensaje devuelto por la operacion.", example = "Operacion realizada correctamente")
    private String mensaje;

    @NotBlank(message = "El campo tipo es obligatorio")
    @Schema(description = "Tipo de notificacion o clasificacion interna.", example = "INFO")
    private String tipo;

    @NotNull(message = "El campo leida es obligatorio")
    @Schema(description = "Indica si la notificacion ya fue leida.", example = "false")
    private Boolean leida;

}
