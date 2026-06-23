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
@Schema(description = "Respuesta entregada por la API para RAM.")
public class RAMResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Tipo de memoria RAM.", example = "DDR5")
    private String tipoMemoria;

    @Schema(description = "Capacidad de la memoria RAM en GB.", example = "16")
    private Integer capacidadGb;

    @Schema(description = "Frecuencia de la memoria RAM en MHz.", example = "6000")
    private Integer frecuenciaMhz;

}
