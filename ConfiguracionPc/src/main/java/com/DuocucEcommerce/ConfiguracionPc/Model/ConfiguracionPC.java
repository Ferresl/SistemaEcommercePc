package com.DuocucEcommerce.ConfiguracionPc.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuraciones_pc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionPC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer cpuId;

    @Column(nullable = false)
    private Integer gpuId;

    @Column(nullable = false)
    private Integer ramId;

    @Column(nullable = false)
    private Integer placaMadreId;

    @Column(nullable = false)
    private Integer almacenamientoId;

    @Column(nullable = false)
    private Integer fuentePoderId;

    @Column(nullable = false)
    private Integer gabineteId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioTotal;

    @Column(nullable = false)
    private Integer tdpTotal;

    @Column(nullable = false)
    private String estado;

}