package DuocucEcommerce.Auth_User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de entrada para inicio de sesion.")
public class LoginRequestDTO {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido, debe contener un @")
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;

    @NotBlank(message = "La password es obligatoria no puede quedar en blanco")
    @Schema(description = "Contrasena enviada por el usuario.", example = "Password123")
    private String password;
}
