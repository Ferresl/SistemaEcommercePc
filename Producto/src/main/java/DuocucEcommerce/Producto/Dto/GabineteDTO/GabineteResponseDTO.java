package DuocucEcommerce.Producto.Dto.GabineteDTO;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para gabinete.")
public class GabineteResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Formato de placa madre soportado por el gabinete.", example = "ATX")
    private String formatoSoportado;

    @Schema(description = "Largo maximo de GPU soportado por el gabinete en milimetros.", example = "330")
    private Integer largoMaxGpuMm;

}
