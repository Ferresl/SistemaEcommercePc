package Duocuc.Ginga.Auth_Service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Duocuc.Ginga.Auth_Service.Model.UsuarioAuth;


@Repository
public interface UsuarioAuthRepository  extends JpaRepository<UsuarioAuth, Integer>{

    Optional<UsuarioAuth> findByEmail (String email);


}
