package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.RAM;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RAMRepository extends JpaRepository<RAM, Integer> {

    Optional<RAM> findByProductoId(Integer productoId);
}