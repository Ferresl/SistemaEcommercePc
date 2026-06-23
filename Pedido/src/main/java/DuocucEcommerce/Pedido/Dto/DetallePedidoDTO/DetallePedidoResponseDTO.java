package DuocucEcommerce.Pedido.Dto.DetallePedidoDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoResponseDTO {

    private Integer id;

    private Integer pedidoId;

    private Integer productoId;

    private Integer cantidad;

    private BigDecimal precioUnitario;

}