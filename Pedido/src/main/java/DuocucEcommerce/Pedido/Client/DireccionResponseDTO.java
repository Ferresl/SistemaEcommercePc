package DuocucEcommerce.Pedido.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Respuesta entregada por la API para direccion.")
public class DireccionResponseDTO { 
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id; 

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId; }

