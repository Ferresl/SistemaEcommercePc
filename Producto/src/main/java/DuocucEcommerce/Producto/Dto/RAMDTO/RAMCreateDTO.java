package DuocucEcommerce.Producto.Dto.RAMDTO;

import jakarta.validation.constraints.Min;
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
@Schema(description = "Datos necesarios para crear RAM.")
public class RAMCreateDTO {
    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo tipoMemoria es obligatorio")
    @Schema(description = "Tipo de memoria RAM.", example = "DDR5")
    private String tipoMemoria;

    @NotNull(message = "El campo capacidadGb es obligatorio")
    @Min(value = 1, message = "El campo capacidadGb debe ser mayor a 0")
    @Schema(description = "Capacidad de la memoria RAM en GB.", example = "16")
    private Integer capacidadGb;

    @NotNull(message = "El campo frecuenciaMhz es obligatorio")
    @Min(value = 1, message = "El campo frecuenciaMhz debe ser mayor a 0")
    @Schema(description = "Frecuencia de la memoria RAM en MHz.", example = "6000")
    private Integer frecuenciaMhz;

}
