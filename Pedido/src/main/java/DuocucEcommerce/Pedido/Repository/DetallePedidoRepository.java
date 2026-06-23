package DuocucEcommerce.Pedido.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import DuocucEcommerce.Pedido.Model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    List<DetallePedido> findByPedidoId(Integer pedidoId);
}