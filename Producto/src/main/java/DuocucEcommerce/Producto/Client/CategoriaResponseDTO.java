package DuocucEcommerce.Producto.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta entregada por la API para categoria.")
public class CategoriaResponseDTO {
    
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;
    
    @Schema(description = "Nombre del registro o del usuario.", example = "Juan")
    private String nombre;
    
    @Schema(description = "Valor de tipo producto.", example = "Ejemplo")
    private String tipoProducto;
}
