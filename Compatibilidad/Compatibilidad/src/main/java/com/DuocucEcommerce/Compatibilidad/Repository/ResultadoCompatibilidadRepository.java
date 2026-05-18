package com.DuocucEcommerce.Compatibilidad.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Compatibilidad.Model.ResultadoCompatibilidad;


public interface ResultadoCompatibilidadRepository extends JpaRepository<ResultadoCompatibilidad, Integer> {

    Optional<ResultadoCompatibilidad> findTopByConfiguracionIdOrderByIdDesc(Integer configuracionId);
}