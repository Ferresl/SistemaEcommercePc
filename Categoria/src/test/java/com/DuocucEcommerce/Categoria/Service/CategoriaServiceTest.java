package com.DuocucEcommerce.Categoria.Service;

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

import com.DuocucEcommerce.Categoria.Dto.CategoriaCreateDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaResponseDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaUpdateDTO;
import com.DuocucEcommerce.Categoria.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Categoria.Model.Categoria;
import com.DuocucEcommerce.Categoria.Model.TipoProducto;
import com.DuocucEcommerce.Categoria.Repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repository; // repositorio simulado

    @InjectMocks
    private CategoriaService service; // servicio real con el repo simulado inyectado

    private Categoria categoriaEjemplo;
    private CategoriaCreateDTO createDTO;
    private CategoriaUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        categoriaEjemplo = new Categoria();
        categoriaEjemplo.setId(1);
        categoriaEjemplo.setNombre("Procesadores");
        categoriaEjemplo.setTipoProducto(TipoProducto.CPU);

        createDTO = CategoriaCreateDTO.builder()
                .nombre("Procesadores")
                .tipoProducto(TipoProducto.CPU)
                .build();

        updateDTO = CategoriaUpdateDTO.builder()
                .nombre("Procesadores")
                .tipoProducto(TipoProducto.CPU)
                .build();
    }

    @Test
    void listar_retornaListaConCategorias() {
        // ARRANGE: creamos la lista manualmente
        List<Categoria> listaFalsa = new ArrayList<>();
        listaFalsa.add(categoriaEjemplo);
        when(repository.findAll()).thenReturn(listaFalsa);

        // ACT
        List<CategoriaResponseDTO> resultado = service.listar();

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("Procesadores", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPorId_encontrado() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(categoriaEjemplo));

        // ACT
        CategoriaResponseDTO resultado = service.buscarPorId(1);

        // ASSERT
        assertEquals(1, resultado.getId());
        assertEquals(TipoProducto.CPU, resultado.getTipoProducto());
    }

    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE
        when(repository.findById(99)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99);
        });

        assertEquals("Categoria no encontrado con id 99", ex.getMessage());
    }

    @Test
    void crear_retornaCategoriaGuardada() {
        // ARRANGE
        when(repository.save(any(Categoria.class))).thenReturn(categoriaEjemplo);

        // ACT
        CategoriaResponseDTO resultado = service.crear(createDTO);

        // ASSERT
        assertEquals("Procesadores", resultado.getNombre());
        verify(repository, times(1)).save(any(Categoria.class));
    }

    @Test
    void actualizar_retornaCategoriaActualizada() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(categoriaEjemplo));
        when(repository.save(categoriaEjemplo)).thenReturn(categoriaEjemplo);

        // ACT
        CategoriaResponseDTO resultado = service.actualizar(1, updateDTO);

        // ASSERT
        assertEquals("Procesadores", resultado.getNombre());
        verify(repository, times(1)).save(categoriaEjemplo);
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE
        when(repository.findById(1)).thenReturn(Optional.of(categoriaEjemplo));

        // ACT + ASSERT
        assertDoesNotThrow(() -> service.eliminar(1));
        verify(repository, times(1)).delete(categoriaEjemplo);
    }
}