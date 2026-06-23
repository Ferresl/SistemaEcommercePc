package DuocucEcommerce.Pedido.Dto.DetallePedidoDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para detalle del pedido.")
public class DetallePedidoResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del pedido asociado.", example = "1")
    private Integer pedidoId;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Cantidad de unidades solicitadas.", example = "2")
    private Integer cantidad;

    @Schema(description = "Precio unitario del producto al momento del pedido.", example = "249990")
    private BigDecimal precioUnitario;

}
