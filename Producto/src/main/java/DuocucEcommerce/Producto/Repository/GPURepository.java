package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.GPU;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GPURepository extends JpaRepository<GPU, Integer> {

    Optional<GPU> findByProductoId(Integer productoId);
}