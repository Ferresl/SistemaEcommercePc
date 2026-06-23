package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Builder
@Schema(description = "Datos necesarios para crear notificacion.")
public class NotificacionCreateDTO { private Integer usuarioId; private String titulo; private String mensaje; private String tipo; private Boolean leida; }

