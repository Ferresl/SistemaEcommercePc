package DuocucEcommerce.Producto.Dto.PlacaMadreDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacaMadreUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotBlank(message = "El campo socket es obligatorio")
    private String socket;

    @NotBlank(message = "El campo chipset es obligatorio")
    private String chipset;

    @NotBlank(message = "El campo tipoRamSoportada es obligatorio")
    private String tipoRamSoportada;

    @NotNull(message = "El campo ramMaximaGb es obligatorio")
    @Min(value = 1, message = "El campo ramMaximaGb debe ser mayor a 0")
    private Integer ramMaximaGb;

    @NotBlank(message = "El campo formato es obligatorio")
    private String formato;

}