package DuocucEcommerce.Pedido.Dto.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {
   
    private Integer id;
   
    private Integer usuarioId;
   
    private Integer direccionId;
   
    private BigDecimal subtotal;
   
    private BigDecimal total;
   
    private String estado;
   
    private String codigoConfirmacion;
   
    private List<DetallePedidoResponseDTO> detalles;
}
