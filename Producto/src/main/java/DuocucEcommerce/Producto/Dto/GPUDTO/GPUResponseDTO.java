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
@Schema(description = "Respuesta entregada por la API para GPU.")
public class GPUResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Memoria dedicada de la tarjeta grafica en GB.", example = "12")
    private Integer memoriaGb;

    @Schema(description = "Largo fisico de la tarjeta grafica en milimetros.", example = "300")
    private Integer largoMm;

    @Schema(description = "Consumo energetico estimado en watts.", example = "180")
    private Integer tdpWatts;

}
