package DuocucEcommerce.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocucEcommerce.User.Dto.DireccionDto.DireccionCreateDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionResponseDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionUpdateDTO;
import DuocucEcommerce.User.Exception.BadRequestException;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Model.Direccion;
import DuocucEcommerce.User.Repository.DireccionRepository;
import DuocucEcommerce.User.Repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class DireccionServiceTest {

    @Mock
    private DireccionRepository repository; // repositorio simulado

    @Mock
    private UsuarioRepository usuarioRepository; // repositorio simulado

    @InjectMocks
    private DireccionService service; // servicio real con mocks inyectados

    private Direccion direccionEjemplo;
    private DireccionCreateDTO createDTO;
    private DireccionUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        direccionEjemplo = new Direccion();
        direccionEjemplo.setId(1);
        direccionEjemplo.setUsuarioId(10);
        direccionEjemplo.setRegion("Metropolitana");
        direccionEjemplo.setComuna("Santiago");
        direccionEjemplo.setCalle("Av. Providencia");
        direccionEjemplo.setNumero("1234");
        direccionEjemplo.setDepartamento("Depto 301");
        direccionEjemplo.setReferencia("Frente al metro");
        direccionEjemplo.setPrincipal(true);

        createDTO = DireccionCreateDTO.builder()
                .usuarioId(10)
                .region("Metropolitana")
                .comuna("Santiago")
                .calle("Av. Providencia")
                .numero("1234")
                .departamento("Depto 301")
                .referencia("Frente al metro")
                .principal(true)
                .build();

        updateDTO = DireccionUpdateDTO.builder()
                .usuarioId(10)
                .region("Metropolitana")
                .comuna("Santiago")
                .calle("Av. Providencia")
                .numero("1234")
                .departamento("Depto 301")
                .referencia("Frente al metro")
                .principal(true)
                .build();
    }

    @Test
    void listar_retornaListaConDirecciones() {
        // ARRANGE
        List<Direccion> listaFalsa = new ArrayList<>();
        listaFalsa.add(direccionEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<DireccionResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("Santiago", resultado.get(0).getComuna());
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(direccionEjemplo));

        // ACT
        DireccionResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Direccion no encontrada con id 99", ex.getMessage());
    }

    @Test
    void listarPorUsuario_retornaDirecciones() {
        // ARRANGE
        when(repository.findByUsuarioId(10)).thenReturn(List.of(direccionEjemplo));

        // ACT
        List<DireccionResponseDTO> resultado = service.listarPorUsuario(10);

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getUsuarioId());
    }

    @Test
    void crear_retornaDireccionGuardada() {
        // ARRANGE
        when(usuarioRepository.existsById(10)).thenReturn(true);
        when(repository.save(any(Direccion.class))).thenReturn(direccionEjemplo);

        // ACT
        DireccionResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals("Av. Providencia", resultado.getCalle());
        verify(repository, times(1)).save(any(Direccion.class));
    }

    @Test
    void crear_usuarioNoExiste_lanzaBadRequest() {
        // ARRANGE
        when(usuarioRepository.existsById(10)).thenReturn(false);

        // ACT + ASSERT
        BadRequestException ex = assertThrows(BadRequestException.class, () -> {
            service.crear(createDTO);
        });

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void actualizar_retornaDireccionActualizada() {
        // ARRANGE
        when(usuarioRepository.existsById(10)).thenReturn(true);
        when(repository.findById(1)).thenReturn(Optional.of(direccionEjemplo));
        when(repository.save(direccionEjemplo)).thenReturn(direccionEjemplo);

        // ACT
        DireccionResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals("Metropolitana", resultado.getRegion());
    }

    @Test 
    void actualizar_direccionNoExiste_lanzaNotFound(){
        //ARRANGE 
        when(repository.findById(99)).thenReturn(Optional.empty());
        
        //ACT 
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class , ()  -> {
            service.actualizar(99 , updateDTO);
        } );

        assertEquals("Direccion no encontrada con id 99", ex.getMessage());

        verify(repository).findById(99);
        verify(repository , never()).save(any(Direccion.class)); 
    }


    @Test 
    void actualizar_UsuarioNoExiste_lanzaBadRequest (){
        //ARRANGE
        when(usuarioRepository.existsById(10)).thenReturn(false);

        //ACT 
        BadRequestException ex = assertThrows(BadRequestException.class , () -> {
            service.actualizar(1 , updateDTO);
        } );

            assertEquals("Usuario no encontrado", ex.getMessage());
        

        verify(usuarioRepository).existsById(10);
        verify(repository, never()).findById(1);;
        verify(repository , never()).save(any(Direccion.class));


    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(direccionEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(direccionEjemplo);
    }

    @Test 
    void eliminar_noExitoso (){
        //ARRANGE 
        when(repository.findById(99)).thenReturn(Optional.empty());

        //ACT 
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.eliminar(99);
        } );
        
        assertEquals("Direccion no encontrada con id 99" , ex.getMessage());

        verify(repository).findById(99);
        verify(repository , never()).delete(any(Direccion.class));


    }
}