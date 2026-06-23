package DuocucEcommerce.Pedido.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import DuocucEcommerce.Pedido.Exception.BadRequestException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventarioClient {
    private final RestTemplate restTemplate;
    public void descontar(

        Integer productoId, 

        Integer cantidad) { 
            try { restTemplate.put("http://localhost:8085/api/inventarios/descontar/" + productoId + "?cantidad=" + cantidad, null); 

            } catch (RestClientException ex) { 
                throw new BadRequestException("Stock insuficiente o inventario no disponible"); } 
            }
}