package DuocucEcommerce.Producto.Dto.ProductoDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar producto.")
public class ProductoUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 80)
    @Schema(description = "Nombre comercial del producto.", example = "Procesador Ryzen 5 7600")
    private String nombre;

    @NotBlank(message = "El campo marca es obligatorio")
    @Size(min = 2, max = 60)
    @Schema(description = "Marca comercial del producto.", example = "AMD")
    private String marca;

    @NotBlank(message = "El campo modelo es obligatorio")
    @Size(min = 2, max = 80)
    @Schema(description = "Modelo especifico del producto.", example = "Ryzen 5 7600")
    private String modelo;

    @NotNull(message = "El campo precio es obligatorio")
    @Positive(message = "El campo precio debe ser mayor a 0")
    @Schema(description = "Precio del producto.", example = "249990")
    private BigDecimal precio;

    @NotNull(message = "El campo categoriaId es obligatorio")
    @Schema(description = "Identificador de la categoria asociada.", example = "1")
    private Integer categoriaId;

    @NotBlank(message = "El campo estado es obligatorio")
    @Schema(description = "Estado de disponibilidad del producto.", example = "ACTIVO")
    private String estado;

}
