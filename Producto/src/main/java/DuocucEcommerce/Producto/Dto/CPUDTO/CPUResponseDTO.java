package DuocucEcommerce.Producto.Dto.CPUDTO;

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
@Schema(description = "Respuesta entregada por la API para cpuresponse.")
public class CPUResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Socket compatible del componente.", example = "AM5")
    private String socket;

    @Schema(description = "Cantidad de nucleos del procesador.", example = "6")
    private Integer nucleos;

    @Schema(description = "Cantidad de hilos del procesador.", example = "12")
    private Integer hilos;

    @Schema(description = "Valor de tdp watts.", example = "1")
    private Integer tdpWatts;

}
