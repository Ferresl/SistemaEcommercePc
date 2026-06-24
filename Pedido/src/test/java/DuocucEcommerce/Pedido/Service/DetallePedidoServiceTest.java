package DuocucEcommerce.Pedido.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoUpdateDTO;
import DuocucEcommerce.Pedido.Exception.ResourceNotFoundException;
import DuocucEcommerce.Pedido.Model.DetallePedido;
import DuocucEcommerce.Pedido.Repository.DetallePedidoRepository;

@ExtendWith(MockitoExtension.class)
class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository repository;

    @InjectMocks
    private DetallePedidoService service;

    private DetallePedido detalle;

    @BeforeEach
    void setUp() {
        detalle = DetallePedido.builder()
                .id(1).pedidoId(10).productoId(20).cantidad(2)
                .precioUnitario(new BigDecimal("1500"))
                .build();
    }

    @Test
    void listarRetornaDetalles() {
        when(repository.findAll()).thenReturn(List.of(detalle));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals(20, respuesta.get(0).getProductoId());
        verify(repository).findAll();
    }

    @Test
    void buscarPorIdRetornaDetalle() {
        when(repository.findById(1)).thenReturn(Optional.of(detalle));

        var respuesta = service.buscarPorId(1);

        assertEquals(10, respuesta.getPedidoId());
        assertEquals(2, respuesta.getCantidad());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void crearGuardaDetalle() {
        var dto = DetallePedidoCreateDTO.builder()
                .pedidoId(10).productoId(20).cantidad(2).precioUnitario(new BigDecimal("1500"))
                .build();
        when(repository.save(any(DetallePedido.class))).thenReturn(detalle);

        var respuesta = service.crear(dto);

        assertEquals(1, respuesta.getId());
        assertEquals(new BigDecimal("1500"), respuesta.getPrecioUnitario());
        verify(repository).save(any(DetallePedido.class));
    }

    @Test
    void actualizarModificaDetalle() {
        var dto = DetallePedidoUpdateDTO.builder()
                .pedidoId(11).productoId(21).cantidad(3).precioUnitario(new BigDecimal("2000"))
                .build();
        var actualizado = DetallePedido.builder()
                .id(1).pedidoId(11).productoId(21).cantidad(3).precioUnitario(new BigDecimal("2000"))
                .build();
        when(repository.findById(1)).thenReturn(Optional.of(detalle));
        when(repository.save(any(DetallePedido.class))).thenReturn(actualizado);

        var respuesta = service.actualizar(1, dto);

        assertEquals(11, respuesta.getPedidoId());
        assertEquals(3, respuesta.getCantidad());
        verify(repository).save(detalle);
    }

    @Test
    void eliminarBorraDetalle() {
        when(repository.findById(1)).thenReturn(Optional.of(detalle));

        service.eliminar(1);

        verify(repository).delete(detalle);
    }
}