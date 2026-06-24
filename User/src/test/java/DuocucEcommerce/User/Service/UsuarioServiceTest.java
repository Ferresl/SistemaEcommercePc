package DuocucEcommerce.User.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioCreateDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioResponseDTO;
import DuocucEcommerce.User.Dto.UsuarioDto.UsuarioUpdateDTO;
import DuocucEcommerce.User.Exception.BadRequestException;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Model.RolUsuario;
import DuocucEcommerce.User.Model.Usuario;
import DuocucEcommerce.User.Repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository; // repositorio simulado

    @InjectMocks
    private UsuarioService service; // servicio real con el repo simulado inyectado

    private Usuario usuarioEjemplo;
    private UsuarioCreateDTO createDTO;
    private UsuarioUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        usuarioEjemplo = new Usuario();
        usuarioEjemplo.setId(1);
        usuarioEjemplo.setNombre("Juan");
        usuarioEjemplo.setApellido("Ferreira");
        usuarioEjemplo.setEmail("juan@correo.cl");
        usuarioEjemplo.setTelefono("+56912345678");
        usuarioEjemplo.setRol(RolUsuario.CLIENTE);
        usuarioEjemplo.setEstado("ACTIVO");

        createDTO = UsuarioCreateDTO.builder()
                .nombre("Juan")
                .apellido("Ferreira")
                .email("juan@correo.cl")
                .telefono("+56912345678")
                .rol(RolUsuario.CLIENTE)
                .estado("ACTIVO")
                .build();

        updateDTO = UsuarioUpdateDTO.builder()
                .nombre("Juan")
                .apellido("Ferreira")
                .email("juan@correo.cl")
                .telefono("+56912345678")
                .rol(RolUsuario.CLIENTE)
                .estado("ACTIVO")
                .build();
    }

    @Test
    void listar_retornaListaConUsuarios() {
        // ARRANGE
        List<Usuario> listaFalsa = new ArrayList<>();
        listaFalsa.add(usuarioEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<UsuarioResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(usuarioEjemplo));

        // ACT
        UsuarioResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
        assertEquals("juan@correo.cl", resultado.getEmail());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Usuario no encontrado con id 99", ex.getMessage());
    }

    @Test
    void buscarPorEmail_encontrado() {
        // ARRANGE
        when(repository.findByEmail("juan@correo.cl")).thenReturn(Optional.of(usuarioEjemplo));

        // ACT
        UsuarioResponseDTO resultado = service.buscarPorEmail("juan@correo.cl");

        // ASSERT
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void crear_retornaUsuarioGuardado() {
        // ARRANGE
        when(repository.existsByEmail("juan@correo.cl")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuarioEjemplo);

        // ACT
        UsuarioResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals("juan@correo.cl", resultado.getEmail());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    void crear_emailDuplicado_lanzaBadRequest() {
        // ARRANGE
        when(repository.existsByEmail("juan@correo.cl")).thenReturn(true);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.crear(createDTO);
        });

        assertEquals("El email ya existe", ex.getMessage());
        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void actualizar_retornaUsuarioActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(usuarioEjemplo));
        when(repository.save(usuarioEjemplo)).thenReturn(usuarioEjemplo);

        // ACT
        UsuarioResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals("ACTIVO", resultado.getEstado());
        verify(repository, times(1)).save(usuarioEjemplo);
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(usuarioEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(usuarioEjemplo);
    }
}