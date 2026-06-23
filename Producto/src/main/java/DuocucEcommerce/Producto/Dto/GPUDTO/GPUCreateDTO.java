package DuocucEcommerce.Producto.Dto.GPUDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPUCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotNull(message = "El campo memoriaGb es obligatorio")
    @Min(value = 1, message = "El campo memoriaGb debe ser mayor a 0")
    private Integer memoriaGb;

    @NotNull(message = "El campo largoMm es obligatorio")
    @Min(value = 1, message = "El campo largoMm debe ser mayor a 0")
    private Integer largoMm;

    @NotNull(message = "El campo tdpWatts es obligatorio")
    @Min(value = 1, message = "El campo tdpWatts debe ser mayor a 0")
    private Integer tdpWatts;

}