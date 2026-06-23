package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.Gabinete;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GabineteRepository extends JpaRepository<Gabinete, Integer> {

    Optional<Gabinete> findByProductoId(Integer productoId);
}