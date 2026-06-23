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
@Schema(description = "Datos necesarios para crear GPU.")
public class GPUCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotNull(message = "El campo memoriaGb es obligatorio")
    @Min(value = 1, message = "El campo memoriaGb debe ser mayor a 0")
    @Schema(description = "Memoria dedicada de la tarjeta grafica en GB.", example = "12")
    private Integer memoriaGb;

    @NotNull(message = "El campo largoMm es obligatorio")
    @Min(value = 1, message = "El campo largoMm debe ser mayor a 0")
    @Schema(description = "Largo fisico de la tarjeta grafica en milimetros.", example = "300")
    private Integer largoMm;

    @NotNull(message = "El campo tdpWatts es obligatorio")
    @Min(value = 1, message = "El campo tdpWatts debe ser mayor a 0")
    @Schema(description = "Consumo energetico estimado en watts.", example = "180")
    private Integer tdpWatts;

}
