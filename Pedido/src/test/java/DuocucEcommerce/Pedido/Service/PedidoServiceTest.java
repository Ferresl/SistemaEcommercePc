package DuocucEcommerce.Pedido.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import DuocucEcommerce.Pedido.Client.DireccionResponseDTO;
import DuocucEcommerce.Pedido.Client.InventarioClient;
import DuocucEcommerce.Pedido.Client.NotificacionClient;
import DuocucEcommerce.Pedido.Client.ProductoClient;
import DuocucEcommerce.Pedido.Client.ProductoResponseDTO;
import DuocucEcommerce.Pedido.Client.UsuarioClient;
import DuocucEcommerce.Pedido.Client.UsuarioResponseDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoRequestDTO;
import DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO.EstadoPedidoUpdateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoUpdateDTO;
import DuocucEcommerce.Pedido.Exception.ResourceNotFoundException;
import DuocucEcommerce.Pedido.Model.DetallePedido;
import DuocucEcommerce.Pedido.Model.Pedido;
import DuocucEcommerce.Pedido.Repository.DetallePedidoRepository;
import DuocucEcommerce.Pedido.Repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private DetallePedidoRepository detalleRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private ProductoClient productoClient;

    @Mock
    private InventarioClient inventarioClient;

    @Mock
    private NotificacionClient notificacionClient;

    @InjectMocks
    private PedidoService service;

    private Pedido pedido;
    private DetallePedido detalle;

    @BeforeEach
    void setUp() {
        pedido = Pedido.builder()
                .id(1).usuarioId(2).direccionId(3)
                .subtotal(new BigDecimal("3000")).total(new BigDecimal("3000"))
                .estado("CREADO").codigoConfirmacion("PED-ABC12345")
                .build();
        detalle = DetallePedido.builder()
                .id(10).pedidoId(1).productoId(20).cantidad(2).precioUnitario(new BigDecimal("1500"))
                .build();
    }

    @Test
    void listarRetornaPedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals(1, respuesta.get(0).getDetalles().size());
    }

    @Test
    void buscarPorIdRetornaPedido() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.buscarPorId(1);

        assertEquals(2, respuesta.getUsuarioId());
        assertEquals(new BigDecimal("3000"), respuesta.getTotal());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void listarPorUsuarioRetornaPedidos() {
        when(pedidoRepository.findByUsuarioId(2)).thenReturn(List.of(pedido));
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.listarPorUsuario(2);

        assertEquals(1, respuesta.size());
        verify(pedidoRepository).findByUsuarioId(2);
    }

    @Test
    void crearValidaClientesDescuentaStockYNotifica() {
        var dto = PedidoCreateDTO.builder()
                .usuarioId(2).direccionId(3)
                .detalles(List.of(new DetallePedidoRequestDTO(20, 2)))
                .build();
        var producto = new ProductoResponseDTO();
        producto.setId(20);
        producto.setNombre("CPU");
        producto.setPrecio(new BigDecimal("1500"));

        when(usuarioClient.obtenerUsuario(2)).thenReturn(new UsuarioResponseDTO());
        when(usuarioClient.obtenerDireccion(3)).thenReturn(new DireccionResponseDTO());
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido guardado = invocation.getArgument(0);
            if (guardado.getId() == null) {
                guardado.setId(1);
            }
            if (guardado.getCodigoConfirmacion() == null) {
                guardado.setCodigoConfirmacion("PED-ABC12345");
            }
            return guardado;
        });
        when(detalleRepository.save(any(DetallePedido.class))).thenReturn(detalle);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.crear(dto);

        assertEquals(new BigDecimal("3000"), respuesta.getTotal());
        assertNotNull(respuesta.getCodigoConfirmacion());
        verify(usuarioClient).obtenerUsuario(2);
        verify(usuarioClient).obtenerDireccion(3);
        verify(inventarioClient).descontar(20, 2);
        verify(notificacionClient).crear(any());
    }

    @Test
    void actualizarModificaPedido() {
        var dto = PedidoUpdateDTO.builder().usuarioId(4).direccionId(5).estado("PAGADO").build();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.actualizar(1, dto);

        assertEquals(4, respuesta.getUsuarioId());
        assertEquals("PAGADO", respuesta.getEstado());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void cambiarEstadoActualizaPedido() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.cambiarEstado(1, new EstadoPedidoUpdateDTO("ENVIADO"));

        assertEquals("ENVIADO", respuesta.getEstado());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void eliminarBorraDetallesYPedido() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        service.eliminar(1);

        verify(detalleRepository).deleteAll(List.of(detalle));
        verify(pedidoRepository).delete(pedido);
    }
}