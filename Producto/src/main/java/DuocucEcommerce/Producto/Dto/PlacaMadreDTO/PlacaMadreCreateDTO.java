package DuocucEcommerce.Producto.Dto.PlacaMadreDTO;

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
@Schema(description = "Datos necesarios para crear placa madre.")
public class PlacaMadreCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotBlank(message = "El campo socket es obligatorio")
    @Schema(description = "Socket compatible del componente.", example = "AM5")
    private String socket;

    @NotBlank(message = "El campo chipset es obligatorio")
    @Schema(description = "Chipset principal de la placa madre.", example = "B650")
    private String chipset;

    @NotBlank(message = "El campo tipoRamSoportada es obligatorio")
    @Schema(description = "Tipo de memoria RAM soportada por la placa madre.", example = "DDR5")
    private String tipoRamSoportada;

    @NotNull(message = "El campo ramMaximaGb es obligatorio")
    @Min(value = 1, message = "El campo ramMaximaGb debe ser mayor a 0")
    @Schema(description = "Memoria RAM maxima soportada en GB.", example = "128")
    private Integer ramMaximaGb;

    @NotBlank(message = "El campo formato es obligatorio")
    @Schema(description = "Formato fisico del componente.", example = "ATX")
    private String formato;

}
