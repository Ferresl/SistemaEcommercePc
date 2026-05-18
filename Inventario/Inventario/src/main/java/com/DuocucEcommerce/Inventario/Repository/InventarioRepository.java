package com.DuocucEcommerce.Inventario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Inventario.Model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    Optional<Inventario> findByProductoId(Integer productoId);
}
