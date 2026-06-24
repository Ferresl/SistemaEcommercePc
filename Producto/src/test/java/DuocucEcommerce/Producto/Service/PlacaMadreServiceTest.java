package DuocucEcommerce.Producto.Service;

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

import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreCreateDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreResponseDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.PlacaMadre;
import DuocucEcommerce.Producto.Repository.PlacaMadreRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class PlacaMadreServiceTest {

    @Mock
    private PlacaMadreRepository repository; // repositorio simulado

    @InjectMocks
    private PlacaMadreService service; // servicio real con el repo simulado inyectado

    private PlacaMadre ejemplo;
    private PlacaMadreCreateDTO createDTO;
    private PlacaMadreUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new PlacaMadre();
        ejemplo.setId(1);
        ejemplo.setProductoId(10);
        ejemplo.setSocket("AM5");
        ejemplo.setChipset("B650");
        ejemplo.setTipoRamSoportada("DDR5");
        ejemplo.setRamMaximaGb(128);
        ejemplo.setFormato("ATX");

        createDTO = PlacaMadreCreateDTO.builder()
                .productoId(10)
                .socket("AM5")
                .chipset("B650")
                .tipoRamSoportada("DDR5")
                .ramMaximaGb(128)
                .formato("ATX")
                .build();

        updateDTO = PlacaMadreUpdateDTO.builder()
                .productoId(10)
                .socket("AM5")
                .chipset("B650")
                .tipoRamSoportada("DDR5")
                .ramMaximaGb(128)
                .formato("ATX")
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE: creamos la lista manualmente
        List<PlacaMadre> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT: llamamos al metodo real del servicio
        List<PlacaMadreResponseDTO> resultado = service.listar();

        // ASSERT: verificamos el resultado
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getProductoId());
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE: el repositorio devuelve un Optional con el registro
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT
        PlacaMadreResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
        assertEquals(10, resultado.getProductoId());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE: el repositorio devuelve un Optional vacio
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Ficha tecnica no encontrada con id 99", ex.getMessage());
    }

    @Test
    void buscarPorProductoId_encontrado() {
        // ARRANGE
        when(repository.findByProductoId(10)).thenReturn(Optional.of(ejemplo));

        // ACT
        PlacaMadreResponseDTO resultado = service.buscarPorProductoId(10);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
    }

    @Test
    void buscarPorProductoId_noEncontrado() {
        // ARRANGE
        when(repository.findByProductoId(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorProductoId(99);
        });

        assertEquals("Ficha tecnica no encontrada para producto 99", ex.getMessage());
    }

    @Test
    void crear_retornaRegistroGuardado() {
        // ARRANGE: el repositorio devuelve el registro al guardarlo
        when(repository.save(any(PlacaMadre.class))).thenReturn(ejemplo);

        // ACT
        PlacaMadreResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
        verify(repository, times(1)).save(any(PlacaMadre.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        PlacaMadreResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
        verify(repository, times(1)).save(ejemplo);
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE: el registro existe
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));

        // ACT + ASSERT: no debe lanzar excepcion
        assertDoesNotThrow(() -> service.eliminar(1));

        verify(repository, times(1)).delete(ejemplo);
    }
}
