package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.CPU;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CPURepository extends JpaRepository<CPU, Integer> {

    Optional<CPU> findByProductoId(Integer productoId);
}