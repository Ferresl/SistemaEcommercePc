package DuocucEcommerce.Producto.Dto.FuentePoderDTO;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear fuente de poder.")
public class FuentePoderCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotNull(message = "El campo potenciaWatts es obligatorio")
    @Min(value = 1, message = "El campo potenciaWatts debe ser mayor a 0")
    @Schema(description = "Potencia nominal de la fuente de poder en watts.", example = "650")
    private Integer potenciaWatts;

    @NotBlank(message = "El campo certificacion es obligatorio")
    @Schema(description = "Certificacion de eficiencia de la fuente de poder.", example = "80 Plus Gold")
    private String certificacion;

    @NotNull(message = "El campo modular es obligatorio")
    @Schema(description = "Indica si la fuente de poder es modular.", example = "true")
    private Boolean modular;

}
