package DuocucEcommerce.Producto.Dto.AlmacenamientoDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenamientoResponseDTO {

    private Integer id;

    private Integer productoId;

    private String tipo;

    private Integer capacidadGb;

    private String interfaz;

}