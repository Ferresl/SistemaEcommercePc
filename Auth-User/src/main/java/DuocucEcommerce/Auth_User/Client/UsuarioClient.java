package DuocucEcommerce.Auth_User.Client;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import DuocucEcommerce.Auth_User.Exception.BadRequestException;

@Component
@RequiredArgsConstructor
public class UsuarioClient {
    private static final Logger log = LoggerFactory.getLogger(UsuarioClient.class);
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8082/api/usuarios";

    public UsuarioResponseDTO crearUsuario(UsuarioCreateRequestDTO dto) {
        try {
            return restTemplate.postForObject(baseUrl, dto, UsuarioResponseDTO.class);
        } catch (RestClientException ex) {
            log.error("No se pudo crear usuario en usuario-service", ex);
            throw new BadRequestException("No se pudo crear el perfil del usuario");
        }
    }
}
