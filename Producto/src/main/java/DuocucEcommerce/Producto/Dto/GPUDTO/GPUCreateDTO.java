package DuocucEcommerce.Producto.Dto.GPUDTO;


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
@Schema(description = "Datos necesarios para crear gpucreate.")
public class GPUCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotNull(message = "El campo memoriaGb es obligatorio")
    @Min(value = 1, message = "El campo memoriaGb debe ser mayor a 0")
    @Schema(description = "Valor de memoria gb.", example = "1")
    private Integer memoriaGb;

    @NotNull(message = "El campo largoMm es obligatorio")
    @Min(value = 1, message = "El campo largoMm debe ser mayor a 0")
    @Schema(description = "Valor de largo mm.", example = "1")
    private Integer largoMm;

    @NotNull(message = "El campo tdpWatts es obligatorio")
    @Min(value = 1, message = "El campo tdpWatts debe ser mayor a 0")
    @Schema(description = "Valor de tdp watts.", example = "1")
    private Integer tdpWatts;

}
