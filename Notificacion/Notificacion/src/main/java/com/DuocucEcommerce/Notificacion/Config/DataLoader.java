package com.DuocucEcommerce.Notificacion.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.Notificacion.Model.Notificacion;
import com.DuocucEcommerce.Notificacion.Repository.NotificacionRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(NotificacionRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Notificacion.builder().usuarioId(1).titulo("Bienvenido a Ginga PC").mensaje("Tu cuenta de prueba esta lista").tipo("SISTEMA").leida(false).build());
            }
        };
    }
}
