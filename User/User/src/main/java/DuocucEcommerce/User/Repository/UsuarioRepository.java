package DuocucEcommerce.User.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import DuocucEcommerce.User.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}
