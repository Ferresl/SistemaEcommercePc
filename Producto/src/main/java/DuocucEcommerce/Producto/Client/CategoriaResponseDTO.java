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
    
    @Schema(description = "Nombre de la categoria.", example = "Procesadores")
    private String nombre;
    
    @Schema(description = "Tipo de producto al que pertenece la categoria.", example = "COMPONENTE")
    private String tipoProducto;
}
