package com.DuocucEcommerce.Compatibilidad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Compatibilidad.Model.RecomendacionComponente;


public interface RecomendacionComponenteRepository extends JpaRepository<RecomendacionComponente, Integer> {

    List<RecomendacionComponente> findByConflictoId(Integer conflictoId);
}