package DuocucEcommerce.Pedido.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
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
    private UsuarioResponseDTO usuario;
    private ProductoResponseDTO producto;

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

        usuario = new UsuarioResponseDTO();
        usuario.setId(2);
        usuario.setEmail("juan.perez@correo.cl");

        producto = new ProductoResponseDTO();
        producto.setId(20);
        producto.setNombre("CPU");
        producto.setPrecio(new BigDecimal("1500"));
    }

    @Test
    void listarRetornaPedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        when(usuarioClient.obtenerUsuario(2)).thenReturn(usuario);
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals("juan.perez@correo.cl", respuesta.get(0).getEmailUsuario());
        assertEquals(1, respuesta.get(0).getDetalles().size());
        assertEquals("CPU", respuesta.get(0).getDetalles().get(0).getNombreProducto());
    }

    @Test
    void buscarPorIdRetornaPedido() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(usuarioClient.obtenerUsuario(2)).thenReturn(usuario);
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.buscarPorId(1);

        assertEquals(2, respuesta.getUsuarioId());
        assertEquals("juan.perez@correo.cl", respuesta.getEmailUsuario());
        assertEquals(new BigDecimal("3000"), respuesta.getTotal());
        assertEquals("CPU", respuesta.getDetalles().get(0).getNombreProducto());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void listarPorUsuarioRetornaPedidos() {
        when(pedidoRepository.findByUsuarioId(2)).thenReturn(List.of(pedido));
        when(usuarioClient.obtenerUsuario(2)).thenReturn(usuario);
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
        when(detalleRepository.findByPedidoId(1)).thenReturn(List.of(detalle));

        var respuesta = service.listarPorUsuario(2);

        assertEquals(1, respuesta.size());
        assertEquals("juan.perez@correo.cl", respuesta.get(0).getEmailUsuario());
        verify(pedidoRepository).findByUsuarioId(2);
    }

    @Test
    void crearValidaClientesDescuentaStockYNotifica() {
        var dto = PedidoCreateDTO.builder()
                .usuarioId(2).direccionId(3)
                .detalles(List.of(new DetallePedidoRequestDTO(20, 2)))
                .build();

        when(usuarioClient.obtenerUsuario(2)).thenReturn(usuario);
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
        assertEquals("juan.perez@correo.cl", respuesta.getEmailUsuario());
        assertEquals("CPU", respuesta.getDetalles().get(0).getNombreProducto());
        assertNotNull(respuesta.getCodigoConfirmacion());
        verify(usuarioClient, times(2)).obtenerUsuario(2);
        verify(usuarioClient).obtenerDireccion(3);
        verify(inventarioClient).descontar(20, 2);
        verify(notificacionClient).crear(any());
    }

    @Test
    void actualizarModificaPedido() {
        var dto = PedidoUpdateDTO.builder().usuarioId(4).direccionId(5).estado("PAGADO").build();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        when(usuarioClient.obtenerUsuario(4)).thenReturn(usuario);
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
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
        when(usuarioClient.obtenerUsuario(2)).thenReturn(usuario);
        when(productoClient.obtenerPorId(20)).thenReturn(producto);
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