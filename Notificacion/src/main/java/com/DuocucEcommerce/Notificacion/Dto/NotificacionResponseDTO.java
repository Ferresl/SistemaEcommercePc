package com.DuocucEcommerce.Notificacion.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para notificacion.")
public class NotificacionResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @Schema(description = "Titulo de la notificacion.", example = "Pedido confirmado")
    private String titulo;

    @Schema(description = "Mensaje devuelto por la operacion.", example = "Operacion realizada correctamente")
    private String mensaje;

    @Schema(description = "Tipo de notificacion o clasificacion interna.", example = "INFO")
    private String tipo;

    @Schema(description = "Indica si la notificacion ya fue leida.", example = "false")
    private Boolean leida;

}
