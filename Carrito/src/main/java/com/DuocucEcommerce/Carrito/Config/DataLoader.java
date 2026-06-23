package com.DuocucEcommerce.Carrito.Config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.Carrito.Model.Carrito;
import com.DuocucEcommerce.Carrito.Repository.CarritoRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(CarritoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Carrito.builder().usuarioId(1).subtotal(BigDecimal.ZERO).total(BigDecimal.ZERO).build());
            }
        };
    }
}
