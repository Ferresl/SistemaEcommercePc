package DuocucEcommerce.Producto.Repository;

import DuocucEcommerce.Producto.Model.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoriaId(Integer categoriaId);

    List<Producto> findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCase(String nombre, String marca);

    List<Producto> findByPrecioBetween(BigDecimal rangoMin, BigDecimal rangoMax);
}