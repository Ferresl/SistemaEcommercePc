package com.DuocucEcommerce.ConfiguracionPc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.ConfiguracionPc.Model.ConfiguracionPC;

public interface ConfiguracionPCRepository extends JpaRepository<ConfiguracionPC, Integer> {

    List<ConfiguracionPC> findByUsuarioId(Integer usuarioId);
}
