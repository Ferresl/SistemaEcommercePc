package com.DuocucEcommerce.Categoria.Config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.Categoria.Model.Categoria;
import com.DuocucEcommerce.Categoria.Model.TipoProducto;
import com.DuocucEcommerce.Categoria.Repository.CategoriaRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(CategoriaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        Categoria.builder().nombre("Procesadores").tipoProducto(TipoProducto.CPU).build(),
                        Categoria.builder().nombre("Tarjetas de Video").tipoProducto(TipoProducto.GPU).build(),
                        Categoria.builder().nombre("Memorias RAM").tipoProducto(TipoProducto.RAM).build(),
                        Categoria.builder().nombre("Placas Madre").tipoProducto(TipoProducto.PLACA_MADRE).build(),
                        Categoria.builder().nombre("Almacenamiento").tipoProducto(TipoProducto.ALMACENAMIENTO).build(),
                        Categoria.builder().nombre("Fuentes de Poder").tipoProducto(TipoProducto.FUENTE).build(),
                        Categoria.builder().nombre("Gabinetes").tipoProducto(TipoProducto.GABINETE).build()
                ));
            }
        };
    }
}
