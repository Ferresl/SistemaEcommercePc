package DuocucEcommerce.Pedido.Dto.PedidoDTO;

import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Datos permitidos para actualizar pedido.")
public class PedidoUpdateDTO {
    
    @NotNull(message = "El usuario es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    
    @NotNull(message = "La direccion es obligatoria")
    @Schema(description = "Identificador de direccion asociado.", example = "1")
    private Integer direccionId;
    
    @NotBlank(message = "El estado es obligatorio")
    @Schema(description = "Estado actual del registro.", example = "ACTIVO")
    private String estado;
}
