package DuocucEcommerce.Producto.Dto.CPUDTO;

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
@Schema(description = "Datos permitidos para actualizar CPU.")
public class CPUUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo socket es obligatorio")
    @Schema(description = "Socket compatible del componente.", example = "AM5")
    private String socket;

    @NotNull(message = "El campo nucleos es obligatorio")
    @Min(value = 1, message = "El campo nucleos debe ser mayor a 0")
    @Schema(description = "Cantidad de nucleos del procesador.", example = "6")
    private Integer nucleos;

    @NotNull(message = "El campo hilos es obligatorio")
    @Min(value = 1, message = "El campo hilos debe ser mayor a 0")
    @Schema(description = "Cantidad de hilos del procesador.", example = "12")
    private Integer hilos;

    @NotNull(message = "El campo tdpWatts es obligatorio")
    @Min(value = 1, message = "El campo tdpWatts debe ser mayor a 0")
    @Schema(description = "Consumo energetico estimado en watts.", example = "180")
    private Integer tdpWatts;

}
