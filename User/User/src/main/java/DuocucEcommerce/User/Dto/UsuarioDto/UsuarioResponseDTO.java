package DuocucEcommerce.User.Dto.UsuarioDto;

import DuocucEcommerce.User.Model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Integer id;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    private RolUsuario rol;

    private String estado;

}
