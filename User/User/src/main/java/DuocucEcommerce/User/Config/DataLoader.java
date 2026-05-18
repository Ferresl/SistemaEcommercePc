package DuocucEcommerce.User.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import DuocucEcommerce.User.Model.Direccion;
import DuocucEcommerce.User.Model.RolUsuario;
import DuocucEcommerce.User.Model.Usuario;
import DuocucEcommerce.User.Repository.DireccionRepository;
import DuocucEcommerce.User.Repository.UsuarioRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(UsuarioRepository usuarioRepository, DireccionRepository direccionRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                usuarioRepository.save(Usuario.builder().nombre("Cliente").apellido("Prueba").email("cliente@gingapc.cl").telefono("912345678").rol(RolUsuario.CLIENTE).estado("ACTIVO").build());
                usuarioRepository.save(Usuario.builder().nombre("Empleado").apellido("Tienda").email("empleado@gingapc.cl").telefono("922222222").rol(RolUsuario.EMPLEADO).estado("ACTIVO").build());
                usuarioRepository.save(Usuario.builder().nombre("Admin").apellido("Sistema").email("admin@gingapc.cl").telefono("933333333").rol(RolUsuario.ADMIN).estado("ACTIVO").build());
            }
            if (direccionRepository.count() == 0) {
                direccionRepository.save(Direccion.builder().usuarioId(1).region("Metropolitana").comuna("Santiago").calle("Av Providencia").numero("1000").departamento("101").referencia("Cerca del metro").principal(true).build());
            }
        };
    }
}
