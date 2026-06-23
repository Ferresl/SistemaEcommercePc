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

    @NotBlank(message = "El nombre es obligatorio , no puede quedar en blanco")
    @Size(min = 2, max = 60)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio , no puede quedar en blanco")
    @Size(min = 2, max = 60, message = "debe contener minimo 2 letras y maximo 60")
    private String apellido;

    @NotBlank(message = "El email es obligatorio, no puede quedar en blanco")
    @Email(message = "El email no es valido, debe contener @")
    private String email;

    @NotBlank(message = "La password es obligatoria, no puede quedar en blanco")
    @Size(min = 6, max = 40 , message ="Debe contener minimo 6 caracteres y maximo 40")
    private String password;
    
    @NotBlank(message = "La confirmacion es obligatoria")
    private String confirmarPassword;

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;
}