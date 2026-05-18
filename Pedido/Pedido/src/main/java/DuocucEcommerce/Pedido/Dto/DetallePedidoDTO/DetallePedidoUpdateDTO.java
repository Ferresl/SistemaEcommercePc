package DuocucEcommerce.Pedido.Dto.DetallePedidoDTO;

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
public class DetallePedidoUpdateDTO {

    @NotNull(message = "El campo pedidoId es obligatorio")
    private Integer pedidoId;

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotNull(message = "El campo cantidad es obligatorio")
    @Min(value = 1, message = "El campo cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El campo precioUnitario es obligatorio")
    @Positive(message = "El campo precioUnitario debe ser mayor a 0")
    private BigDecimal precioUnitario;

}