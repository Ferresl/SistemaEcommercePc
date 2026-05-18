package com.DuocucEcommerce.BenchmarkFps.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.BenchmarkFps.Model.Benchmark;

public interface BenchmarkRepository extends JpaRepository<Benchmark, Integer> {

    Optional<Benchmark> findByCpuIdAndGpuIdAndVideojuegoIdAndResolucionId(Integer cpuId, Integer gpuId, Integer videojuegoId, Integer resolucionId);

    List<Benchmark> findByCpuIdAndGpuId(Integer cpuId, Integer gpuId);

    List<Benchmark> findByGpuId(Integer gpuId);
}
