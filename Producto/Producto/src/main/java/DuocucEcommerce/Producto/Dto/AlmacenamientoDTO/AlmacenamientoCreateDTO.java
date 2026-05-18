package DuocucEcommerce.Producto.Dto.AlmacenamientoDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenamientoCreateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotBlank(message = "El campo tipo es obligatorio")
    private String tipo;

    @NotNull(message = "El campo capacidadGb es obligatorio")
    @Min(value = 1, message = "El campo capacidadGb debe ser mayor a 0")
    private Integer capacidadGb;

    @NotBlank(message = "El campo interfaz es obligatorio")
    private String interfaz;

}
