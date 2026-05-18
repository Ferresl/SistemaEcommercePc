package DuocucEcommerce.Pedido.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import DuocucEcommerce.Pedido.Exception.BadRequestException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final RestTemplate restTemplate;

    public UsuarioResponseDTO obtenerUsuario(Integer id) { 
        try { return restTemplate.getForObject("http://localhost:8082/api/usuarios/" + id, UsuarioResponseDTO.class); 

        } catch (RestClientException ex) { 
            throw new BadRequestException("Usuario no encontrado"); } 
        }


    public DireccionResponseDTO obtenerDireccion(Integer id) { 
        try { return restTemplate.getForObject("http://localhost:8082/api/direcciones/" + id, DireccionResponseDTO.class); 

        } catch (RestClientException ex) { 
            throw new BadRequestException("Direccion no encontrada"); } 
        }
}
