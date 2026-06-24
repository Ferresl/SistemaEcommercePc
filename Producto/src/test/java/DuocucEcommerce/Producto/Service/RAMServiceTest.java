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

import DuocucEcommerce.Producto.Dto.RAMDTO.RAMCreateDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMResponseDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.RAM;
import DuocucEcommerce.Producto.Repository.RAMRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class RAMServiceTest {

    @Mock
    private RAMRepository repository; // repositorio simulado

    @InjectMocks
    private RAMService service; // servicio real con el repo simulado inyectado

    private RAM ejemplo;
    private RAMCreateDTO createDTO;
    private RAMUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new RAM();
        ejemplo.setId(1);
        ejemplo.setProductoId(10);
        ejemplo.setTipoMemoria("DDR5");
        ejemplo.setCapacidadGb(32);
        ejemplo.setFrecuenciaMhz(6000);

        createDTO = RAMCreateDTO.builder()
                .productoId(10)
                .tipoMemoria("DDR5")
                .capacidadGb(32)
                .frecuenciaMhz(6000)
                .build();

        updateDTO = RAMUpdateDTO.builder()
                .productoId(10)
                .tipoMemoria("DDR5")
                .capacidadGb(32)
                .frecuenciaMhz(6000)
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE: creamos la lista manualmente
        List<RAM> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT: llamamos al metodo real del servicio
        List<RAMResponseDTO> resultado = service.listar();

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
        RAMResponseDTO resultado = service.buscarPorId(1);

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
        RAMResponseDTO resultado = service.buscarPorProductoId(10);

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
        when(repository.save(any(RAM.class))).thenReturn(ejemplo);

        // ACT
        RAMResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
        verify(repository, times(1)).save(any(RAM.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        RAMResponseDTO resultado = service.actualizar(1, updateDTO);

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
