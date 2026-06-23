package com.DuocucEcommerce.Compatibilidad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Compatibilidad.Model.ConflictoCompatibilidad;

public interface ConflictoCompatibilidadRepository extends JpaRepository<ConflictoCompatibilidad, Integer> {

    List<ConflictoCompatibilidad> findByResultadoId(Integer resultadoId);
}