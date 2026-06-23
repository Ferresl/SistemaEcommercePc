package DuocucEcommerce.Pedido.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import DuocucEcommerce.Pedido.Exception.BadRequestException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoClient {
    private final RestTemplate restTemplate;
    public ProductoResponseDTO obtenerPorId(Integer id) { 
        try { return restTemplate.getForObject("http://localhost:8083/api/productos/" + id, ProductoResponseDTO.class); 

        } catch (RestClientException ex) { 
            throw new BadRequestException("Producto no encontrado"); } }
}
