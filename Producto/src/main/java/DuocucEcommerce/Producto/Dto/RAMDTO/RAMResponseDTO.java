package DuocucEcommerce.Producto.Dto.RAMDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RAMResponseDTO {

    private Integer id;

    private Integer productoId;

    private String tipoMemoria;

    private Integer capacidadGb;

    private Integer frecuenciaMhz;

}