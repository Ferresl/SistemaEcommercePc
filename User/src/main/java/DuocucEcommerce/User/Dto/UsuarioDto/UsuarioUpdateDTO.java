package DuocucEcommerce.User.Dto.UsuarioDto;

import DuocucEcommerce.User.Model.RolUsuario;
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
@Schema(description = "Datos permitidos para actualizar usuario.")
public class UsuarioUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 60)
    @Schema(description = "Nombre del usuario.", example = "Juan")
    private String nombre;

    @NotBlank(message = "El campo apellido es obligatorio")
    @Size(min = 2, max = 60)
    @Schema(description = "Apellido del usuario.", example = "Ferreira")
    private String apellido;

    @NotBlank(message = "El campo email es obligatorio")
    @Email
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;

    @Size(max = 20)
    @Schema(description = "Telefono de contacto del usuario.", example = "+56912345678")
    private String telefono;

    @NotNull(message = "El campo rol es obligatorio")
    @Schema(description = "Rol asignado al usuario.", example = "CLIENTE")
    private RolUsuario rol;

    @NotBlank(message = "El campo estado es obligatorio")
    @Schema(description = "Estado de la cuenta del usuario.", example = "ACTIVO")
    private String estado;

}
