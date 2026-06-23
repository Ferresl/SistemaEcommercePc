package DuocucEcommerce.Producto.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import DuocucEcommerce.Producto.Model.Almacenamiento;

public interface AlmacenamientoRepository extends JpaRepository<Almacenamiento, Integer> {

    Optional<Almacenamiento> findByProductoId(Integer productoId);
}
