package com.DuocucEcommerce.Comparador.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.Comparador.Model.Comparacion;
import com.DuocucEcommerce.Comparador.Model.ItemComparacion;
import com.DuocucEcommerce.Comparador.Repository.ComparacionRepository;
import com.DuocucEcommerce.Comparador.Repository.ItemComparacionRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(ComparacionRepository comparacionRepository, ItemComparacionRepository itemRepository) {
        return args -> {
            if (comparacionRepository.count() == 0) {
                Comparacion c = comparacionRepository.save(Comparacion.builder().usuarioId(1).categoriaId(1).build());
                itemRepository.save(ItemComparacion.builder().comparacionId(c.getId()).productoId(1).build());
                itemRepository.save(ItemComparacion.builder().comparacionId(c.getId()).productoId(2).build());
            }
        };
    }
}