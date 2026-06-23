package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.FuentePoder;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuentePoderRepository extends JpaRepository<FuentePoder, Integer> {

    Optional<FuentePoder> findByProductoId(Integer productoId);
}