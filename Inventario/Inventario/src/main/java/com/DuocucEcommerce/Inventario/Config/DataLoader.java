package com.DuocucEcommerce.Inventario.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.Inventario.Model.Inventario;
import com.DuocucEcommerce.Inventario.Repository.InventarioRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(InventarioRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                for (int productoId = 1; productoId <= 14; productoId++) {
                    repository.save(Inventario.builder().productoId(productoId).stockDisponible(10).stockReservado(0).stockMinimo(2).build());
                }
            }
        };
    }
}