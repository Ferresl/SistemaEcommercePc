package com.DuocucEcommerce.Comparador.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Comparador.Model.Comparacion;

public interface ComparacionRepository extends JpaRepository<Comparacion, Integer> {

    List<Comparacion> findByUsuarioId(Integer usuarioId);
}
