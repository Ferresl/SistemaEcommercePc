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
   
    @Schema(description = "Identificador de direccion asociado.", example = "1")
    private Integer direccionId;
   
    @Schema(description = "Subtotal de la linea o item.", example = "399990")
    private BigDecimal subtotal;
   
    @Schema(description = "Monto total calculado.", example = "799990")
    private BigDecimal total;
   
    @Schema(description = "Estado actual del registro.", example = "ACTIVO")
    private String estado;
   
    @Schema(description = "Valor de codigo confirmacion.", example = "Ejemplo")
    private String codigoConfirmacion;
   
    @Schema(description = "Valor de detalles.", example = "Ejemplo")
    private List<DetallePedidoResponseDTO> detalles;
}
