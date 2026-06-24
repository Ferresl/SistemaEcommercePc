package com.DuocucEcommerce.Comparador.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.Comparador.Client.CategoriaClient;
import com.DuocucEcommerce.Comparador.Client.ProductoClient;
import com.DuocucEcommerce.Comparador.Client.ProductoResponseDTO;
import com.DuocucEcommerce.Comparador.Client.UsuarioClient;
import com.DuocucEcommerce.Comparador.Dto.AgregarItemComparacionDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Exception.BadRequestException;
import com.DuocucEcommerce.Comparador.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Comparador.Model.Comparacion;
import com.DuocucEcommerce.Comparador.Model.ItemComparacion;
import com.DuocucEcommerce.Comparador.Repository.ComparacionRepository;
import com.DuocucEcommerce.Comparador.Repository.ItemComparacionRepository;

@ExtendWith(MockitoExtension.class)
class ComparacionServiceTest {

    @Mock
    private ComparacionRepository comparacionRepository;

    @Mock
    private ItemComparacionRepository itemRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private CategoriaClient categoriaClient;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private ComparacionService service;

    private Comparacion comparacion;
    private ItemComparacion itemUno;
    private ItemComparacion itemDos;

    @BeforeEach
    void setUp() {
        comparacion = Comparacion.builder().id(1).usuarioId(2).categoriaId(3).build();
        itemUno = ItemComparacion.builder().id(10).comparacionId(1).productoId(100).build();
        itemDos = ItemComparacion.builder().id(11).comparacionId(1).productoId(101).build();
    }

    @Test
    void buscarPorIdRetornaComparacionCuandoTieneMinimoDeItems() {
        when(comparacionRepository.findById(1)).thenReturn(Optional.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno, itemDos));

        var respuesta = service.buscarPorId(1);

        assertEquals(1, respuesta.getId());
        assertEquals(2, respuesta.getItems().size());
        verify(comparacionRepository).findById(1);
    }

    @Test
    void buscarPorIdConMenosDeDosItemsLanzaBadRequest() {
        when(comparacionRepository.findById(1)).thenReturn(Optional.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno));

        assertThrows(BadRequestException.class, () -> service.buscarPorId(1));
    }

    @Test
    void buscarPorIdInexistenteLanzaResourceNotFound() {
        when(comparacionRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void listarPorUsuarioRetornaComparaciones() {
        when(comparacionRepository.findByUsuarioId(2)).thenReturn(List.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno, itemDos));

        var respuesta = service.listarPorUsuario(2);

        assertEquals(1, respuesta.size());
        assertEquals(3, respuesta.get(0).getCategoriaId());
        verify(comparacionRepository).findByUsuarioId(2);
    }

    @Test
    void crearValidaUsuarioCategoriaYGuarda() {
        var dto = ComparacionCreateDTO.builder().usuarioId(2).categoriaId(3).build();
        when(comparacionRepository.save(any(Comparacion.class))).thenReturn(comparacion);
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of());

        var respuesta = service.crear(dto);

        assertEquals(2, respuesta.getUsuarioId());
        assertEquals(3, respuesta.getCategoriaId());
        verify(usuarioClient).validar(2);
        verify(categoriaClient).validar(3);
        verify(comparacionRepository).save(any(Comparacion.class));
    }

    @Test
    void agregarItemGuardaCuandoCategoriaCoincide() {
        var dto = new AgregarItemComparacionDTO(100);
        var producto = new ProductoResponseDTO();
        producto.setId(100);
        producto.setCategoriaId(3);
        when(comparacionRepository.findById(1)).thenReturn(Optional.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno, itemDos));
        when(productoClient.obtener(100)).thenReturn(producto);
        when(itemRepository.save(any(ItemComparacion.class))).thenReturn(itemUno);

        var respuesta = service.agregarItem(1, dto);

        assertEquals(1, respuesta.getId());
        verify(productoClient).obtener(100);
        verify(itemRepository).save(any(ItemComparacion.class));
    }

    @Test
    void agregarItemConCuatroProductosLanzaBadRequest() {
        var dto = new AgregarItemComparacionDTO(104);
        when(comparacionRepository.findById(1)).thenReturn(Optional.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno, itemDos, itemUno, itemDos));

        assertThrows(BadRequestException.class, () -> service.agregarItem(1, dto));
    }

    @Test
    void agregarItemConCategoriaDistintaLanzaBadRequest() {
        var dto = new AgregarItemComparacionDTO(100);
        var producto = new ProductoResponseDTO();
        producto.setId(100);
        producto.setCategoriaId(4);
        when(comparacionRepository.findById(1)).thenReturn(Optional.of(comparacion));
        when(itemRepository.findByComparacionId(1)).thenReturn(List.of(itemUno));
        when(productoClient.obtener(100)).thenReturn(producto);

        assertThrows(BadRequestException.class, () -> service.agregarItem(1, dto));
    }

    @Test
    void eliminarItemBorraRegistro() {
        when(itemRepository.findById(10)).thenReturn(Optional.of(itemUno));

        service.eliminarItem(10);

        verify(itemRepository).delete(itemUno);
    }
}