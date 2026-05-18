package DuocucEcommerce.Producto.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponseDTO {
    
    private Integer id;
    
    private String nombre;
    
    private String tipoProducto;
}