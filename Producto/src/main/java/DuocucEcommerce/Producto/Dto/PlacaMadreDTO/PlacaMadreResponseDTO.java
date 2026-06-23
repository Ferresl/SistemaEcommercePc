package DuocucEcommerce.Producto.Dto.PlacaMadreDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para placa madre.")
public class PlacaMadreResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Socket compatible del componente.", example = "AM5")
    private String socket;

    @Schema(description = "Chipset principal del componente.", example = "B650")
    private String chipset;

    @Schema(description = "Valor de tipo ram soportada.", example = "Ejemplo")
    private String tipoRamSoportada;

    @Schema(description = "Valor de ram maxima gb.", example = "1")
    private Integer ramMaximaGb;

    @Schema(description = "Formato fisico del componente.", example = "ATX")
    private String formato;

}
