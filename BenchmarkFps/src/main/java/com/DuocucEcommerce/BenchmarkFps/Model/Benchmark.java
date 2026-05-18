package com.DuocucEcommerce.BenchmarkFps.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "benchmarks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benchmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer cpuId;

    @Column(nullable = false)
    private Integer gpuId;

    @Column(nullable = false)
    private Integer videojuegoId;

    @Column(nullable = false)
    private Integer resolucionId;

    @Column(nullable = false)
    private Double fpsPromedio;

}
