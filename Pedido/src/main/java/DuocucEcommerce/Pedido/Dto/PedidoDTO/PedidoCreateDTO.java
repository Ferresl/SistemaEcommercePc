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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoCreateDTO {
    
    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;
    
    @NotNull(message = "La direccion es obligatoria")
    private Integer direccionId;
    
    @Valid
    @NotEmpty(message = "Debe agregar productos al pedido")
    private List<DetallePedidoRequestDTO> detalles;
}
