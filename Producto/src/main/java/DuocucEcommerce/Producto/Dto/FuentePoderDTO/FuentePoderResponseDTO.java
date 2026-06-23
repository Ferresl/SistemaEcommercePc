package DuocucEcommerce.Producto.Dto.FuentePoderDTO;

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
@Schema(description = "Respuesta entregada por la API para fuente poder.")
public class FuentePoderResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Valor de potencia watts.", example = "1")
    private Integer potenciaWatts;

    @Schema(description = "Certificacion del componente.", example = "80 Plus Gold")
    private String certificacion;

    @Schema(description = "Valor de modular.", example = "true")
    private Boolean modular;

}
