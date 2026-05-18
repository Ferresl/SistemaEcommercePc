package DuocucEcommerce.Auth_User.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import DuocucEcommerce.Auth_User.Model.RolUsuario;
import DuocucEcommerce.Auth_User.Model.UsuarioAuth;
import DuocucEcommerce.Auth_User.Repository.UsuarioAuthRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(UsuarioAuthRepository repository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(UsuarioAuth.builder().usuarioId(1).email("cliente@gingapc.cl").passwordHash(passwordEncoder.encode("Cliente123")).rol(RolUsuario.CLIENTE).estado("ACTIVO").build());
                repository.save(UsuarioAuth.builder().usuarioId(2).email("empleado@gingapc.cl").passwordHash(passwordEncoder.encode("Empleado123")).rol(RolUsuario.EMPLEADO).estado("ACTIVO").build());
                repository.save(UsuarioAuth.builder().usuarioId(3).email("admin@gingapc.cl").passwordHash(passwordEncoder.encode("Admin123")).rol(RolUsuario.ADMIN).estado("ACTIVO").build());
            }
        };
    }
}