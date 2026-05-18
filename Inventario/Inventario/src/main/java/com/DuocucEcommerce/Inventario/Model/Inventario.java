package com.DuocucEcommerce.Inventario.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable= false, unique=false)
    private Integer productoId;
    
    @Column(nullable= false)
    private Integer stockDisponible;
    
    @Column(nullable= false)
    private Integer stockReservado;
    
    @Column(nullable= false)
    private Integer stockMinimo;



}
