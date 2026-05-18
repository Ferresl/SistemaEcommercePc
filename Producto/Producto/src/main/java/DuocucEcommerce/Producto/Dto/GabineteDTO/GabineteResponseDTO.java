package DuocucEcommerce.Producto.Dto.GabineteDTO;

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
public class GabineteResponseDTO {

    private Integer id;

    private Integer productoId;

    private String formatoSoportado;

    private Integer largoMaxGpuMm;

}