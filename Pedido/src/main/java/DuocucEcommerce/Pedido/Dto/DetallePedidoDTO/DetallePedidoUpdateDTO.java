package DuocucEcommerce.Pedido.Dto.DetallePedidoDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar detalle pedido.")
public class DetallePedidoUpdateDTO {

    @NotNull(message = "El campo pedidoId es obligatorio")
    @Schema(description = "Identificador del pedido asociado.", example = "1")
    private Integer pedidoId;

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotNull(message = "El campo cantidad es obligatorio")
    @Min(value = 1, message = "El campo cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de unidades.", example = "2")
    private Integer cantidad;

    @NotNull(message = "El campo precioUnitario es obligatorio")
    @Positive(message = "El campo precioUnitario debe ser mayor a 0")
    @Schema(description = "Valor de precio unitario.", example = "249990")
    private BigDecimal precioUnitario;

}
