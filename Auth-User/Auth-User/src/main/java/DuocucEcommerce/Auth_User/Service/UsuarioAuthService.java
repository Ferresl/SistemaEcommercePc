package DuocucEcommerce.Auth_User.Service;
 
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Auth_User.Client.UsuarioClient;
import DuocucEcommerce.Auth_User.Client.UsuarioCreateRequestDTO;
import DuocucEcommerce.Auth_User.Client.UsuarioResponseDTO;
import DuocucEcommerce.Auth_User.Dto.LoginRequestDTO;
import DuocucEcommerce.Auth_User.Dto.RegistroRequestDTO;
import DuocucEcommerce.Auth_User.Dto.UsuarioAuthResponseDTO;
import DuocucEcommerce.Auth_User.Exception.BadRequestException;
import DuocucEcommerce.Auth_User.Model.UsuarioAuth;
import DuocucEcommerce.Auth_User.Repository.UsuarioAuthRepository;

@Service
@RequiredArgsConstructor
public class UsuarioAuthService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioAuthService.class);
    private final UsuarioAuthRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsuarioClient usuarioClient;

    public UsuarioAuthResponseDTO registrar(RegistroRequestDTO dto) {
        log.info("Registrando usuario con email {}", dto.getEmail());

        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new BadRequestException("Las passwords no coinciden");
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("El email ya esta registrado");
        }

        UsuarioCreateRequestDTO usuarioDto = UsuarioCreateRequestDTO.builder()
                .nombre(dto.getNombre()).apellido(dto.getApellido()).email(dto.getEmail())
                .telefono("").rol(dto.getRol()).estado("ACTIVO").build();
        UsuarioResponseDTO usuario = usuarioClient.crearUsuario(usuarioDto);
        String hash = passwordEncoder.encode(dto.getPassword());
        UsuarioAuth auth = UsuarioAuth.builder()
                .usuarioId(usuario.getId()).email(dto.getEmail()).passwordHash(hash)
                .rol(dto.getRol()).estado("ACTIVO").build();
        auth = repository.save(auth);
        if (!passwordEncoder.matches(dto.getPassword(), auth.getPasswordHash())) {
            throw new BadRequestException("No se pudo validar el hash de la password");
        }
        return toResponse(auth);
    }

    public UsuarioAuthResponseDTO login(LoginRequestDTO dto) {
        UsuarioAuth auth = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadRequestException("Credenciales invalidas"));
        if (!passwordEncoder.matches(dto.getPassword(), auth.getPasswordHash())) {
            log.warn("Credenciales invalidas para {}", dto.getEmail());
            throw new BadRequestException("Credenciales invalidas");
        }
        return toResponse(auth);
    }

    public Boolean validarToken(String token) {
        return jwtService.validarToken(token);
    }

    private UsuarioAuthResponseDTO toResponse(UsuarioAuth auth) {
        return UsuarioAuthResponseDTO.builder()
                .usuarioId(auth.getUsuarioId()).email(auth.getEmail())
                .rol(auth.getRol()).token(jwtService.generarToken(auth)).build();
    }
}