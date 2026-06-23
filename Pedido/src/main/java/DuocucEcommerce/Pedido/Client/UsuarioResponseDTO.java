package DuocucEcommerce.Pedido.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Respuesta entregada por la API para usuario.")
public class UsuarioResponseDTO { 
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id; 
    
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email; 
}
