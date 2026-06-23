package DuocucEcommerce.Producto.Exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta usada cuando la API informa un error.")
public class ErrorResponseDTO {
    @Schema(description = "Mensaje devuelto por la operacion.", example = "Operacion realizada correctamente")
    private String mensaje;
    @Schema(description = "Valor de status.", example = "1")
    private Integer status;
    @Schema(description = "Valor de timestamp.", example = "Ejemplo")
    private LocalDateTime timestamp;
}
