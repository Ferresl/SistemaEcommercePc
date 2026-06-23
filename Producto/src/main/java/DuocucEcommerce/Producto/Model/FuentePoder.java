package DuocucEcommerce.Producto.Model;

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
@Table(name = "fuentes_poder")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuentePoder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private Integer potenciaWatts;

    @Column(nullable = false)
    private String certificacion;

    @Column(nullable = false)
    private Boolean modular;

}
