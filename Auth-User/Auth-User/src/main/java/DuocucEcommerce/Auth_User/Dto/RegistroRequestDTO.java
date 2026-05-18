package DuocucEcommerce.Auth_User.Dto;

import DuocucEcommerce.Auth_User.Model.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 60)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 60)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido")
    private String email;

    @NotBlank(message = "La password es obligatoria")
    @Size(min = 6, max = 40)
    
    private String password;
    @NotBlank(message = "La confirmacion es obligatoria")
    private String confirmarPassword;

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;
}