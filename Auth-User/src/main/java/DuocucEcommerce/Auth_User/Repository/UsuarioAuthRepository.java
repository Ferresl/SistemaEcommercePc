package DuocucEcommerce.Auth_User.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import DuocucEcommerce.Auth_User.Model.UsuarioAuth;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Integer>{

    Optional<UsuarioAuth> findByEmail(String email);

    Boolean existsByEmail(String email);

}
