package DuocucEcommerce.Producto.Dto.CPUDTO;

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
public class CPUResponseDTO {

    private Integer id;

    private Integer productoId;

    private String socket;

    private Integer nucleos;

    private Integer hilos;

    private Integer tdpWatts;

}