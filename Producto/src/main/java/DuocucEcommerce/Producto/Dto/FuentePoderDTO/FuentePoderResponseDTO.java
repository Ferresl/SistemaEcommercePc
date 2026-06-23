package DuocucEcommerce.Producto.Dto.FuentePoderDTO;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuentePoderResponseDTO {

    private Integer id;

    private Integer productoId;

    private Integer potenciaWatts;

    private String certificacion;

    private Boolean modular;

}