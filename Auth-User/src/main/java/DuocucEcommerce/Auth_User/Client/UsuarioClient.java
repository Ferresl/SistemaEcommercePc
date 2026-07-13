package DuocucEcommerce.Auth_User.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuario-service")
public interface UsuarioClient {

    @PostMapping("/api/usuarios")
    UsuarioResponseDTO crearUsuario(@RequestBody UsuarioCreateRequestDTO dto);
}
