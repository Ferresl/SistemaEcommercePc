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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCreateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 60)
    private String nombre;

    @NotBlank(message = "El campo apellido es obligatorio")
    @Size(min = 2, max = 60)
    private String apellido;

    @NotBlank(message = "El campo email es obligatorio")
    @Email
    private String email;

    @Size(max = 20)
    private String telefono;

    @NotNull(message = "El campo rol es obligatorio")
    private RolUsuario rol;

    @NotBlank(message = "El campo estado es obligatorio")
    private String estado;

}
