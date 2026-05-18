package com.DuocucEcommerce.Notificacion.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private String mensaje;
    private Integer status;
    private LocalDateTime timestamp;
}
