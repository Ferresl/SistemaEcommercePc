package DuocucEcommerce.User.Dto.DireccionDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionUpdateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    private Integer usuarioId;

    @NotBlank(message = "El campo region es obligatorio")
    private String region;

    @NotBlank(message = "El campo comuna es obligatorio")
    private String comuna;

    @NotBlank(message = "El campo calle es obligatorio")
    private String calle;

    @NotBlank(message = "El campo numero es obligatorio")
    private String numero;

    private String departamento;

    private String referencia;

    @NotNull(message = "El campo principal es obligatorio")
    private Boolean principal;

}
