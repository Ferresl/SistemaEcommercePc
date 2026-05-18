package com.DuocucEcommerce.Carrito.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Carrito.Model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    Optional<Carrito> findByUsuarioId(Integer usuarioId);
}
