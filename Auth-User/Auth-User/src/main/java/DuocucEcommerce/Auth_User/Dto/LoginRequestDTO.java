package DuocucEcommerce.Auth_User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido, debe contener un @")
    private String email;

    @NotBlank(message = "La password es obligatoria no puede quedar en blanco")
    private String password;
}
