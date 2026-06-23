package com.DuocucEcommerce.Compatibilidad.Model;

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
@Table(name = "resultados_compatibilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoCompatibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer configuracionId;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false, length = 500)
    private String mensaje;

}