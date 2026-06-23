package DuocucEcommerce.Pedido.Dto.DetallePedidoDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de entrada para detalle pedido.")
public class DetallePedidoRequestDTO {
   
    @NotNull(message = "El producto es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;
   
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de unidades.", example = "2")
    private Integer cantidad;
}
