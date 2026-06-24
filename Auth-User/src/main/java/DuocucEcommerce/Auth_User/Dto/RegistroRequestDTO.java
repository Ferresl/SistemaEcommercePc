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
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de entrada para registro de usuario.")
public class RegistroRequestDTO {

    @NotBlank(message = "El nombre es obligatorio , no puede quedar en blanco")
    @Size(min = 2, max = 60)
    @Schema(description = "Nombre del usuario.", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio , no puede quedar en blanco")
    @Size(min = 2, max = 60, message = "debe contener minimo 2 letras y maximo 60")
    @Schema(description = "Apellido del usuario.", example = "Ferreira")
    private String apellido;

    @NotBlank(message = "El email es obligatorio, no puede quedar en blanco")
    @Email(message = "El email no es valido, debe contener @")
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;

    @NotBlank(message = "La password es obligatoria, no puede quedar en blanco")
    @Size(min = 6, max = 40 , message ="Debe contener minimo 6 caracteres y maximo 40")
    @Schema(description = "Contrasena enviada por el usuario.", example = "Password123")
    private String password;
    
    @NotBlank(message = "La confirmacion es obligatoria")
    @Schema(description = "Confirmacion de la contrasena ingresada.", example = "Password123")
    private String confirmarPassword;

    @NotNull(message = "El rol es obligatorio")
    @Schema(description = "Rol asignado al usuario.", example = "CLIENTE")
    private RolUsuario rol;
}
