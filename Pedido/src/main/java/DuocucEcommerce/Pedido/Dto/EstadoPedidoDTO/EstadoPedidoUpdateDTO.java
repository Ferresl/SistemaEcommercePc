package DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos permitidos para actualizar estado del pedido.")
public class EstadoPedidoUpdateDTO {
    
    @NotBlank(message = "El estado es obligatorio")
    @Schema(description = "Estado actual del pedido.", example = "PENDIENTE")
    private String estado;

}
