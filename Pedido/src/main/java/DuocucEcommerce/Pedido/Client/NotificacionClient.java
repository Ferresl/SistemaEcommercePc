package DuocucEcommerce.Pedido.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificacionClient {
    private static final Logger log = LoggerFactory.getLogger(NotificacionClient.class);
    
    private final RestTemplate restTemplate;

    public void crear(NotificacionCreateDTO dto) { 
        try { restTemplate.postForObject("http://localhost:8092/api/notificaciones", dto, Object.class); 

        } catch (Exception ex) { 
            log.warn("No se pudo crear notificacion"); } }
}