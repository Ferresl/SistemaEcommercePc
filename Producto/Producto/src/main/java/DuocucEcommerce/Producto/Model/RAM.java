package DuocucEcommerce.Producto.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private String tipoMemoria;

    @Column(nullable = false)
    private Integer capacidadGb;

    @Column(nullable = false)
    private Integer frecuenciaMhz;

}
