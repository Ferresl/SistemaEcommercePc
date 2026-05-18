package com.DuocucEcommerce.Notificacion.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionResponseDTO {

    private Integer id;

    private Integer usuarioId;

    private String titulo;

    private String mensaje;

    private String tipo;

    private Boolean leida;

}
