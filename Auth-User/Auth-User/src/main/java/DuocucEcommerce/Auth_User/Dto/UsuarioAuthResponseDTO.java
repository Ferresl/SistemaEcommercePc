package DuocucEcommerce.Auth_User.Dto;


import DuocucEcommerce.Auth_User.Model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioAuthResponseDTO {

    private Integer usuarioId;
    private String email;
    private RolUsuario rol;
    private String token;
}

