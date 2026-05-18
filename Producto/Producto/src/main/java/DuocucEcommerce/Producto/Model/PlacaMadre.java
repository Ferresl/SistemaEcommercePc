package DuocucEcommerce.Producto.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "placas_madre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacaMadre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private String socket;

    @Column(nullable = false)
    private String chipset;

    @Column(nullable = false)
    private String tipoRamSoportada;

    @Column(nullable = false)
    private Integer ramMaximaGb;

    @Column(nullable = false)
    private String formato;

}
