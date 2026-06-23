package DuocucEcommerce.Producto.Dto.AlmacenamientoDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para almacenamiento.")
public class AlmacenamientoResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Tipo de notificacion o clasificacion interna.", example = "INFO")
    private String tipo;

    @Schema(description = "Valor de capacidad gb.", example = "1")
    private Integer capacidadGb;

    @Schema(description = "Valor de interfaz.", example = "Ejemplo")
    private String interfaz;

}
