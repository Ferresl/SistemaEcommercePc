package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.PlacaMadre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlacaMadreRepository extends JpaRepository<PlacaMadre, Integer> {

    Optional<PlacaMadre> findByProductoId(Integer productoId);
}