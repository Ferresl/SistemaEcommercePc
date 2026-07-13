package DuocucEcommerce.Pedido.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacion-service")
public interface NotificacionClient {

    @PostMapping("/api/notificaciones")
    void crear(@RequestBody NotificacionCreateDTO dto);
}
