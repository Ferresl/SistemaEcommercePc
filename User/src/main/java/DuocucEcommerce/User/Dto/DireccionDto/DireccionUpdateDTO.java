package DuocucEcommerce.User.Dto.DireccionDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar direccion.")
public class DireccionUpdateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @NotBlank(message = "El campo region es obligatorio")
    @Schema(description = "Region de la direccion.", example = "Metropolitana")
    private String region;

    @NotBlank(message = "El campo comuna es obligatorio")
    @Schema(description = "Comuna de la direccion.", example = "Santiago")
    private String comuna;

    @NotBlank(message = "El campo calle es obligatorio")
    @Schema(description = "Calle de la direccion.", example = "Av. Providencia")
    private String calle;

    @NotBlank(message = "El campo numero es obligatorio")
    @Schema(description = "Numero de la direccion.", example = "1234")
    private String numero;

    @Schema(description = "Departamento o referencia interna.", example = "Depto 301")
    private String departamento;

    @Schema(description = "Referencia adicional de ubicacion.", example = "Frente al metro")
    private String referencia;

    @NotNull(message = "El campo principal es obligatorio")
    @Schema(description = "Indica si es la direccion principal del usuario.", example = "true")
    private Boolean principal;

}
