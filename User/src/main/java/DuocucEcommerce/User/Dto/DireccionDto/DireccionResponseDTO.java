package DuocucEcommerce.User.Dto.DireccionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para direccion.")
public class DireccionResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @Schema(description = "Region de la direccion.", example = "Metropolitana")
    private String region;

    @Schema(description = "Comuna de la direccion.", example = "Santiago")
    private String comuna;

    @Schema(description = "Calle de la direccion.", example = "Av. Providencia")
    private String calle;

    @Schema(description = "Numero de la direccion.", example = "1234")
    private String numero;

    @Schema(description = "Departamento o referencia interna.", example = "Depto 301")
    private String departamento;

    @Schema(description = "Referencia adicional de ubicacion.", example = "Frente al metro")
    private String referencia;

    @Schema(description = "Indica si es la direccion principal del usuario.", example = "true")
    private Boolean principal;

}
