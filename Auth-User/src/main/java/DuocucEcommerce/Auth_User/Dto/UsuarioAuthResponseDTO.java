package DuocucEcommerce.Auth_User.Dto;


import DuocucEcommerce.Auth_User.Model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para autenticacion de usuario.")
public class UsuarioAuthResponseDTO {

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;
    @Schema(description = "Rol asignado al usuario.", example = "CLIENTE")
    private RolUsuario rol;
    @Schema(description = "Token JWT generado para la sesion.", example = "eyJhbGciOiJIUzI1NiJ9")
    private String token;
}

