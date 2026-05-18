package DuocucEcommerce.Pedido.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import DuocucEcommerce.Pedido.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByUsuarioId(Integer usuarioId);
}