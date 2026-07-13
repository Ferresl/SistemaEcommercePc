package DuocucEcommerce.Pedido.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventario-service")
public interface InventarioClient {

    @PutMapping("/api/inventarios/descontar/{productoId}")
    void descontar(@PathVariable("productoId") Integer productoId, @RequestParam("cantidad") Integer cantidad);
}
