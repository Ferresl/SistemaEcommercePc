package DuocucEcommerce.Producto.Dto.CPUDTO;

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
public class CPUUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotBlank(message = "El campo socket es obligatorio")
    private String socket;

    @NotNull(message = "El campo nucleos es obligatorio")
    @Min(value = 1, message = "El campo nucleos debe ser mayor a 0")
    private Integer nucleos;

    @NotNull(message = "El campo hilos es obligatorio")
    @Min(value = 1, message = "El campo hilos debe ser mayor a 0")
    private Integer hilos;

    @NotNull(message = "El campo tdpWatts es obligatorio")
    @Min(value = 1, message = "El campo tdpWatts debe ser mayor a 0")
    private Integer tdpWatts;

}