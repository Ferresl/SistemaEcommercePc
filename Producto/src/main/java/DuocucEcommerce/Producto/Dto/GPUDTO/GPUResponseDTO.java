package DuocucEcommerce.Producto.Dto.GPUDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para gpuresponse.")
public class GPUResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Valor de memoria gb.", example = "1")
    private Integer memoriaGb;

    @Schema(description = "Valor de largo mm.", example = "1")
    private Integer largoMm;

    @Schema(description = "Valor de tdp watts.", example = "1")
    private Integer tdpWatts;

}
