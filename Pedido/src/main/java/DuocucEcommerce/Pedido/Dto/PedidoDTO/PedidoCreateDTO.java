package DuocucEcommerce.Pedido.Dto.PedidoDTO;

import java.util.List;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear pedido.")
public class PedidoCreateDTO {
    
    @NotNull(message = "El usuario es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    
    @NotNull(message = "La direccion es obligatoria")
    @Schema(description = "Identificador de la direccion de entrega.", example = "1")
    private Integer direccionId;
    
    @Valid
    @NotEmpty(message = "Debe agregar productos al pedido")
    @Schema(description = "Detalle de productos incluidos en el pedido.", example = "[]")
    private List<DetallePedidoRequestDTO> detalles;
}
