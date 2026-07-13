package DuocucEcommerce.Producto.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "categoria-service")
public interface CategoriaClient {

    @GetMapping("/api/categorias/{id}")
    CategoriaResponseDTO obtenerPorId(@PathVariable("id") Integer id);
}
