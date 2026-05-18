package DuocucEcommerce.User.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import DuocucEcommerce.User.Model.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    List<Direccion> findByUsuarioId(Integer usuarioId);
}
