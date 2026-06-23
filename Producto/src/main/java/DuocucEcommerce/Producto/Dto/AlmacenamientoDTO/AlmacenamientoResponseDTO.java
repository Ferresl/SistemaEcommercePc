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

    @Schema(description = "Tipo de almacenamiento.", example = "SSD")
    private String tipo;

    @Schema(description = "Capacidad del almacenamiento en GB.", example = "1000")
    private Integer capacidadGb;

    @Schema(description = "Interfaz de conexion del almacenamiento.", example = "NVMe")
    private String interfaz;

}
