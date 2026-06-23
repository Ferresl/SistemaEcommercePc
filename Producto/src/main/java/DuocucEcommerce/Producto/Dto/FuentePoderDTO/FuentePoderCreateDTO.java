package DuocucEcommerce.Producto.Dto.FuentePoderDTO;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuentePoderCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotNull(message = "El campo potenciaWatts es obligatorio")
    @Min(value = 1, message = "El campo potenciaWatts debe ser mayor a 0")
    private Integer potenciaWatts;

    @NotBlank(message = "El campo certificacion es obligatorio")
    private String certificacion;

    @NotNull(message = "El campo modular es obligatorio")
    private Boolean modular;

}