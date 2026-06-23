package DuocucEcommerce.Producto.Dto.GabineteDTO;

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
@Schema(description = "Datos permitidos para actualizar gabinete.")
public class GabineteUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo formatoSoportado es obligatorio")
    @Schema(description = "Valor de formato soportado.", example = "Ejemplo")
    private String formatoSoportado;

    @NotNull(message = "El campo largoMaxGpuMm es obligatorio")
    @Min(value = 1, message = "El campo largoMaxGpuMm debe ser mayor a 0")
    @Schema(description = "Valor de largo max gpu mm.", example = "1")
    private Integer largoMaxGpuMm;

}
