package DuocucEcommerce.Producto.Dto.RAMDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RAMCreateDTO {
    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotBlank(message = "El campo tipoMemoria es obligatorio")
    private String tipoMemoria;

    @NotNull(message = "El campo capacidadGb es obligatorio")
    @Min(value = 1, message = "El campo capacidadGb debe ser mayor a 0")
    private Integer capacidadGb;

    @NotNull(message = "El campo frecuenciaMhz es obligatorio")
    @Min(value = 1, message = "El campo frecuenciaMhz debe ser mayor a 0")
    private Integer frecuenciaMhz;

}