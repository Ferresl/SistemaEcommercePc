package DuocucEcommerce.Producto.Dto.AlmacenamientoDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear almacenamiento.")
public class AlmacenamientoCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo tipo es obligatorio")
    @Schema(description = "Tipo de notificacion o clasificacion interna.", example = "INFO")
    private String tipo;

    @NotNull(message = "El campo capacidadGb es obligatorio")
    @Min(value = 1, message = "El campo capacidadGb debe ser mayor a 0")
    @Schema(description = "Valor de capacidad gb.", example = "1")
    private Integer capacidadGb;

    @NotBlank(message = "El campo interfaz es obligatorio")
    @Schema(description = "Valor de interfaz.", example = "Ejemplo")
    private String interfaz;

}
