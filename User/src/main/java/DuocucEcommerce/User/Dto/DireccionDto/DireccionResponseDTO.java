package DuocucEcommerce.User.Dto.DireccionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionResponseDTO {

    private Integer id;

    private Integer usuarioId;

    private String region;

    private String comuna;

    private String calle;

    private String numero;

    private String departamento;

    private String referencia;

    private Boolean principal;

}