package com.DuocucEcommerce.Comparador.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Comparador.Model.ItemComparacion;

public interface ItemComparacionRepository extends JpaRepository<ItemComparacion, Integer> {

    List<ItemComparacion> findByComparacionId(Integer comparacionId);
}