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

import DuocucEcommerce.Producto.Dto.GPUDTO.GPUCreateDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUResponseDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.GPU;
import DuocucEcommerce.Producto.Repository.GPURepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class GPUServiceTest {

    @Mock
    private GPURepository repository; // repositorio simulado

    @InjectMocks
    private GPUService service; // servicio real con el repo simulado inyectado

    private GPU ejemplo;
    private GPUCreateDTO createDTO;
    private GPUUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        ejemplo = new GPU();
        ejemplo.setId(1);
        ejemplo.setProductoId(10);
        ejemplo.setMemoriaGb(12);
        ejemplo.setLargoMm(280);
        ejemplo.setTdpWatts(200);

        createDTO = GPUCreateDTO.builder()
                .productoId(10)
                .memoriaGb(12)
                .largoMm(280)
                .tdpWatts(200)
                .build();

        updateDTO = GPUUpdateDTO.builder()
                .productoId(10)
                .memoriaGb(12)
                .largoMm(280)
                .tdpWatts(200)
                .build();
    }

    @Test
    void listar_retornaListaConRegistros() {
        // ARRANGE: creamos la lista manualmente
        List<GPU> listaFalsa = new ArrayList<>();
        listaFalsa.add(ejemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT: llamamos al metodo real del servicio
        List<GPUResponseDTO> resultado = service.listar();

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
        GPUResponseDTO resultado = service.buscarPorId(1);

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
        GPUResponseDTO resultado = service.buscarPorProductoId(10);

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
        when(repository.save(any(GPU.class))).thenReturn(ejemplo);

        // ACT
        GPUResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals(10, resultado.getProductoId());
        verify(repository, times(1)).save(any(GPU.class));
    }

    @Test
    void actualizar_retornaRegistroActualizado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(ejemplo));
        when(repository.save(ejemplo)).thenReturn(ejemplo);

        // ACT
        GPUResponseDTO resultado = service.actualizar(1, updateDTO);

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
