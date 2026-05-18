package com.DuocucEcommerce.BenchmarkFps.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DuocucEcommerce.BenchmarkFps.Model.Benchmark;
import com.DuocucEcommerce.BenchmarkFps.Model.Resolucion;
import com.DuocucEcommerce.BenchmarkFps.Model.Videojuego;
import com.DuocucEcommerce.BenchmarkFps.Repository.BenchmarkRepository;
import com.DuocucEcommerce.BenchmarkFps.Repository.ResolucionRepository;
import com.DuocucEcommerce.BenchmarkFps.Repository.VideojuegoRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(VideojuegoRepository videojuegoRepository, ResolucionRepository resolucionRepository, BenchmarkRepository benchmarkRepository) {
        return args -> {
            if (videojuegoRepository.count() == 0) {
                videojuegoRepository.save(Videojuego.builder().nombre("Fortnite").build());
                videojuegoRepository.save(Videojuego.builder().nombre("Cyberpunk 2077").build());
            }
            if (resolucionRepository.count() == 0) {
                resolucionRepository.save(Resolucion.builder().nombre("1080p").build());
                resolucionRepository.save(Resolucion.builder().nombre("1440p").build());
            }
            if (benchmarkRepository.count() == 0) {
                benchmarkRepository.save(Benchmark.builder().cpuId(1).gpuId(3).videojuegoId(1).resolucionId(1).fpsPromedio(145.0).build());
                benchmarkRepository.save(Benchmark.builder().cpuId(2).gpuId(4).videojuegoId(1).resolucionId(1).fpsPromedio(130.0).build());
                benchmarkRepository.save(Benchmark.builder().cpuId(1).gpuId(3).videojuegoId(2).resolucionId(1).fpsPromedio(72.0).build());
            }
        };
    }
}
