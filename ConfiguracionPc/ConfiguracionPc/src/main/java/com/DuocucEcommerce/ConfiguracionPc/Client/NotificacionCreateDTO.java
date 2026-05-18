package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class NotificacionCreateDTO { private Integer usuarioId; private String titulo; private String mensaje; private String tipo; private Boolean leida; }

