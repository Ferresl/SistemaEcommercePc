package DuocucEcommerce.Producto.Dto.GPUDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPUResponseDTO {

    private Integer id;

    private Integer productoId;

    private Integer memoriaGb;

    private Integer largoMm;

    private Integer tdpWatts;

}