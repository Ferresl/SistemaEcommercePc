package DuocucEcommerce.Pedido.Dto.PedidoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoUpdateDTO {
    
    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;
    
    @NotNull(message = "La direccion es obligatoria")
    private Integer direccionId;
    
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}