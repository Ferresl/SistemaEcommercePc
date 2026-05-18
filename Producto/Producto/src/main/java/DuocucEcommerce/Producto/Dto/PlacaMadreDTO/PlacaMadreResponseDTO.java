package DuocucEcommerce.Producto.Dto.PlacaMadreDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacaMadreResponseDTO {

    private Integer id;

    private Integer productoId;

    private String socket;

    private String chipset;

    private String tipoRamSoportada;

    private Integer ramMaximaGb;

    private String formato;

}