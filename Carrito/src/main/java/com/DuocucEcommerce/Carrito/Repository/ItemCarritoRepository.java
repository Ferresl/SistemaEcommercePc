package com.DuocucEcommerce.Carrito.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Carrito.Model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Integer> {

    List<ItemCarrito> findByCarritoId(Integer carritoId);
}
