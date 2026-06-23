package DuocucEcommerce.Pedido.Service;


import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoUpdateDTO;
import DuocucEcommerce.Pedido.Exception.ResourceNotFoundException;
import DuocucEcommerce.Pedido.Model.DetallePedido;
import DuocucEcommerce.Pedido.Repository.DetallePedidoRepository;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    
    private static final Logger log = LoggerFactory.getLogger(DetallePedidoService.class);
    private final DetallePedidoRepository repository;

    
    public List<DetallePedidoResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public DetallePedidoResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public DetallePedidoResponseDTO crear(DetallePedidoCreateDTO dto) {
        log.info("Creando detalle pedido");
        DetallePedido detallePedido = new DetallePedido();
        copiarDatos(dto, detallePedido);
        return toResponse(repository.save(detallePedido));
    }
    public DetallePedidoResponseDTO actualizar(Integer id, DetallePedidoUpdateDTO dto) {
        log.info("Actualizando detalle pedido con id {}", id);
        DetallePedido detallePedido = obtenerEntidad(id);
        copiarDatos(dto, detallePedido);
        return toResponse(repository.save(detallePedido));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private DetallePedido obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetallePedido no encontrado con id " + id));
    }
    private void copiarDatos(DetallePedidoCreateDTO dto, DetallePedido detallePedido) {
        detallePedido.setPedidoId(dto.getPedidoId());
        detallePedido.setProductoId(dto.getProductoId());
        detallePedido.setCantidad(dto.getCantidad());
        detallePedido.setPrecioUnitario(dto.getPrecioUnitario());
    }
    private void copiarDatos(DetallePedidoUpdateDTO dto, DetallePedido detallePedido) {
        detallePedido.setPedidoId(dto.getPedidoId());
        detallePedido.setProductoId(dto.getProductoId());
        detallePedido.setCantidad(dto.getCantidad());
        detallePedido.setPrecioUnitario(dto.getPrecioUnitario());
    }
    private DetallePedidoResponseDTO toResponse(DetallePedido detallePedido) {
        return DetallePedidoResponseDTO.builder()
                .id(detallePedido.getId())
                .pedidoId(detallePedido.getPedidoId())
                .productoId(detallePedido.getProductoId())
                .cantidad(detallePedido.getCantidad())
                .precioUnitario(detallePedido.getPrecioUnitario())
                .build();
    }
}