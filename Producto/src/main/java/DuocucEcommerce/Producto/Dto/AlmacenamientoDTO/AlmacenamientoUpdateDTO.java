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
@Schema(description = "Datos permitidos para actualizar almacenamiento.")
public class AlmacenamientoUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo tipo es obligatorio")
    @Schema(description = "Tipo de almacenamiento.", example = "SSD")
    private String tipo;

    @NotNull(message = "El campo capacidadGb es obligatorio")
    @Min(value = 1, message = "El campo capacidadGb debe ser mayor a 0")
    @Schema(description = "Capacidad del almacenamiento en GB.", example = "1000")
    private Integer capacidadGb;

    @NotBlank(message = "El campo interfaz es obligatorio")
    @Schema(description = "Interfaz de conexion del almacenamiento.", example = "NVMe")
    private String interfaz;

}
