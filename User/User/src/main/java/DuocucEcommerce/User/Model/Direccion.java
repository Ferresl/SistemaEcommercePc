package DuocucEcommerce.User.Model;

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
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String comuna;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String numero;

    private String departamento;

    private String referencia;

    @Column(nullable = false)
    private Boolean principal;

}