package DuocucEcommerce.Auth_User.Client;

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
@Schema(description = "Datos de entrada para usuario create.")
public class UsuarioCreateRequestDTO {
    @Schema(description = "Nombre del usuario.", example = "Juan")
    private String nombre;
    @Schema(description = "Apellido del usuario.", example = "Ferreira")
    private String apellido;
    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;
    @Schema(description = "Telefono de contacto del usuario.", example = "+56912345678")
    private String telefono;
    @Schema(description = "Rol asignado al usuario.", example = "CLIENTE")
    private RolUsuario rol;
    @Schema(description = "Estado de la cuenta del usuario.", example = "ACTIVO")
    private String estado;
}
