package DuocucEcommerce.Pedido.Dto.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para pedido.")
public class PedidoResponseDTO {
   
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;
   
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String emailUsuario;
   
    @Schema(description = "Identificador de la direccion de entrega.", example = "1")
    private Integer direccionId;
   
    @Schema(description = "Subtotal calculado antes del total final.", example = "399990")
    private BigDecimal subtotal;
   
    @Schema(description = "Monto total calculado.", example = "799990")
    private BigDecimal total;
   
    @Schema(description = "Estado actual del pedido.", example = "PENDIENTE")
    private String estado;
   
    @Schema(description = "Codigo de confirmacion del pedido.", example = "PED-20260623-0001")
    private String codigoConfirmacion;
   
    @Schema(description = "Detalle de productos incluidos en el pedido.", example = "[]")
    private List<DetallePedidoResponseDTO> detalles;
}
