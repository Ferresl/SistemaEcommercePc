package DuocucEcommerce.Producto.Dto.ProductoDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCreateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 80)
    private String nombre;

    @NotBlank(message = "El campo marca es obligatorio")
    @Size(min = 2, max = 60)
    private String marca;

    @NotBlank(message = "El campo modelo es obligatorio")
    @Size(min = 2, max = 80)
    private String modelo;

    @NotNull(message = "El campo precio es obligatorio")
    @Positive(message = "El campo precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El campo categoriaId es obligatorio")
    private Integer categoriaId;

    @NotBlank(message = "El campo estado es obligatorio")
    private String estado;

}