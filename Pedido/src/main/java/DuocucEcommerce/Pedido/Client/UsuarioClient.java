package DuocucEcommerce.Pedido.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioResponseDTO obtenerUsuario(@PathVariable("id") Integer id);

    @GetMapping("/api/direcciones/{id}")
    DireccionResponseDTO obtenerDireccion(@PathVariable("id") Integer id);
}
