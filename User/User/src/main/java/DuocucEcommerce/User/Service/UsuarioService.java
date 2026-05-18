package DuocucEcommerce.User.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioCreateDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioResponseDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioUpdateDTO;
import DuocucEcommerce.User.Exception.BadRequestException;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Model.Usuario;
import DuocucEcommerce.User.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repository;

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Integer id) {
        return toResponse(obtenerEntidad(id));
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email " + email));
    }

    public UsuarioResponseDTO crear(UsuarioCreateDTO dto) {
        log.info("Creando usuario {}", dto.getEmail());

        if (repository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("El email ya existe");
        }

        Usuario usuario = new Usuario();
        copiarDatos(dto, usuario);
        Usuario usuarioGuardado = repository.save(usuario);

        return toResponse(usuarioGuardado);
    }

    public UsuarioResponseDTO actualizar(Integer id, UsuarioUpdateDTO dto) {
        Usuario usuario = obtenerEntidad(id);

        if (!usuario.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("El email ya existe");
        }

        copiarDatos(dto, usuario);

        Usuario usuarioActualizado = repository.save(usuario);

        return toResponse(usuarioActualizado);
    }

    public void eliminar(Integer id) {
        Usuario usuario = obtenerEntidad(id);
        repository.delete(usuario);
    }

    private Usuario obtenerEntidad(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id " + id));
    }

    private void copiarDatos(UsuarioCreateDTO dto, Usuario usuario) {
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setEstado(dto.getEstado());
    }

    private void copiarDatos(UsuarioUpdateDTO dto, Usuario usuario) {
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setEstado(dto.getEstado());
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .rol(usuario.getRol())
                .estado(usuario.getEstado())
                .build();
    }
}