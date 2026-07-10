package DuocucEcommerce.Pedido.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import DuocucEcommerce.Pedido.Client.InventarioClient;
import DuocucEcommerce.Pedido.Client.NotificacionClient;
import DuocucEcommerce.Pedido.Client.NotificacionCreateDTO;
import DuocucEcommerce.Pedido.Client.ProductoClient;
import DuocucEcommerce.Pedido.Client.ProductoResponseDTO;
import DuocucEcommerce.Pedido.Client.UsuarioClient;
import DuocucEcommerce.Pedido.Client.UsuarioResponseDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoRequestDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO.EstadoPedidoUpdateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoResponseDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoUpdateDTO;
import DuocucEcommerce.Pedido.Exception.ResourceNotFoundException;
import DuocucEcommerce.Pedido.Model.DetallePedido;
import DuocucEcommerce.Pedido.Model.Pedido;
import DuocucEcommerce.Pedido.Repository.DetallePedidoRepository;
import DuocucEcommerce.Pedido.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detalleRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;
    private final NotificacionClient notificacionClient;

    
    public List<PedidoResponseDTO> listar() { 
        return pedidoRepository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public PedidoResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerPedido(id)); 
    }
    
    public List<PedidoResponseDTO> listarPorUsuario(Integer usuarioId) { 
        return pedidoRepository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList(); 
    }
    
    public PedidoResponseDTO crear(PedidoCreateDTO dto) {
        usuarioClient.obtenerUsuario(dto.getUsuarioId()); 
        usuarioClient.obtenerDireccion(dto.getDireccionId());

        Pedido pedido = Pedido.builder()
        .usuarioId(dto.getUsuarioId())
        .direccionId(dto.getDireccionId())
        .subtotal(BigDecimal.ZERO).total(BigDecimal.ZERO)
        .estado("CREADO").codigoConfirmacion("PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()).build();
        pedido = pedidoRepository.save(pedido);
        BigDecimal subtotal = BigDecimal.ZERO;
        
        for (DetallePedidoRequestDTO item : dto.getDetalles()) {
            ProductoResponseDTO producto = productoClient.obtenerPorId(item.getProductoId());
            inventarioClient.descontar(item.getProductoId(), item.getCantidad());
            detalleRepository.save(DetallePedido.builder().pedidoId(pedido.getId()).productoId(item.getProductoId()).cantidad(item.getCantidad()).precioUnitario(producto.getPrecio()).build());
            subtotal = subtotal.add(producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
        }
        pedido.setSubtotal(subtotal); pedido.setTotal(subtotal); pedido = pedidoRepository.save(pedido);
        notificacionClient.crear(NotificacionCreateDTO.builder().usuarioId(dto.getUsuarioId()).titulo("Pedido creado").mensaje("Tu pedido " + pedido.getCodigoConfirmacion() + " fue creado").tipo("PEDIDO").leida(false).build());
        return toResponse(pedido);
    }
   
    public PedidoResponseDTO actualizar(Integer id, PedidoUpdateDTO dto) { 
        Pedido pedido = obtenerPedido(id); pedido.setUsuarioId(dto.getUsuarioId()); 
        pedido.setDireccionId(dto.getDireccionId()); pedido.setEstado(dto.getEstado()); 
        return toResponse(pedidoRepository.save(pedido)); 
    }
   
    public PedidoResponseDTO cambiarEstado(Integer id, EstadoPedidoUpdateDTO dto) { 
        Pedido pedido = obtenerPedido(id);
         pedido.setEstado(dto.getEstado()); 
         return toResponse(pedidoRepository.save(pedido)); 
        }
   
    public void eliminar(Integer id) { 
        Pedido pedido = obtenerPedido(id); 
        detalleRepository.deleteAll(detalleRepository.findByPedidoId(id)); 
        pedidoRepository.delete(pedido); 
    }
   
    private Pedido obtenerPedido(Integer id) { 
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + id)); 
    }
   
    private DetallePedidoResponseDTO detalleResponse(DetallePedido d) { 
        ProductoResponseDTO producto = productoClient.obtenerPorId(d.getProductoId());
        return DetallePedidoResponseDTO.builder()
        .id(d.getId())
        .pedidoId(d.getPedidoId())
        .productoId(d.getProductoId())
        .nombreProducto(producto.getNombre())
        .cantidad(d.getCantidad())
        .precioUnitario(d.getPrecioUnitario())
        .build(); 
    }
   
    private PedidoResponseDTO toResponse(Pedido p) { 
        UsuarioResponseDTO usuario = usuarioClient.obtenerUsuario(p.getUsuarioId());
        return PedidoResponseDTO.builder()
        .id(p.getId())
        .usuarioId(p.getUsuarioId())
        .emailUsuario(usuario.getEmail())
        .direccionId(p.getDireccionId())
        .subtotal(p.getSubtotal())
        .total(p.getTotal())
        .estado(p.getEstado())
        .codigoConfirmacion(p.getCodigoConfirmacion())
        .detalles(detalleRepository.findByPedidoId(p.getId())
                    .stream().map(this::detalleResponse)
                    .toList())
                    .build(); 
                }
}
