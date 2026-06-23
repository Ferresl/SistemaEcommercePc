package DuocucEcommerce.User.Dto.UsuarioDto;

import DuocucEcommerce.User.Model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para usuario.")
public class UsuarioResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Nombre del registro o del usuario.", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del usuario.", example = "Ferreira")
    private String apellido;

    @Schema(description = "Correo electronico del usuario.", example = "juan.ferreira@correo.cl")
    private String email;

    @Schema(description = "Telefono de contacto.", example = "+56912345678")
    private String telefono;

    @Schema(description = "Rol asignado al usuario.", example = "CLIENTE")
    private RolUsuario rol;

    @Schema(description = "Estado actual del registro.", example = "ACTIVO")
    private String estado;

}
