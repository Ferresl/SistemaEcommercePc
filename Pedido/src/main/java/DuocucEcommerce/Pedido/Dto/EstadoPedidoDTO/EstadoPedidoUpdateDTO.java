package DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoPedidoUpdateDTO {
    
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

}
