package DuocucEcommerce.Auth_User.Client;

import DuocucEcommerce.Auth_User.Model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private RolUsuario rol;
    private String estado;
}
