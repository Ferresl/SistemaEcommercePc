package DuocucEcommerce.Producto.Dto.GabineteDTO;

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
public class GabineteCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotBlank(message = "El campo formatoSoportado es obligatorio")
    private String formatoSoportado;

    @NotNull(message = "El campo largoMaxGpuMm es obligatorio")
    @Min(value = 1, message = "El campo largoMaxGpuMm debe ser mayor a 0")
    private Integer largoMaxGpuMm;

}