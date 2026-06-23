package DuocucEcommerce.Producto.Dto.RAMDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para ramresponse.")
public class RAMResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Valor de tipo memoria.", example = "Ejemplo")
    private String tipoMemoria;

    @Schema(description = "Valor de capacidad gb.", example = "1")
    private Integer capacidadGb;

    @Schema(description = "Valor de frecuencia mhz.", example = "1")
    private Integer frecuenciaMhz;

}
