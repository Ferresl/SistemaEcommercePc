package DuocucEcommerce.Producto.Dto.ProductoDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {

    private Integer id;

    private String nombre;

    private String marca;

    private String modelo;

    private BigDecimal precio;


    private Integer categoriaId;

    private String estado;

}