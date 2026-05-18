package DuocucEcommerce.Producto.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import DuocucEcommerce.Producto.Exception.BadRequestException;
import lombok.RequiredArgsConstructor;





@Component
@RequiredArgsConstructor
public class CategoriaClient {
    private static final Logger log = LoggerFactory.getLogger(CategoriaClient.class);
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8084/api/categorias";

    public CategoriaResponseDTO obtenerPorId(Integer id) {
        try {
            return restTemplate.getForObject(baseUrl + "/" + id, CategoriaResponseDTO.class);
        } catch (RestClientException ex) {
            log.warn("Categoria no encontrada con id {}", id);
            throw new BadRequestException("Categoria no encontrada");
        }
    }
}
