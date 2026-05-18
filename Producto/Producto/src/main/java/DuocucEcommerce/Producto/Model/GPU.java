package DuocucEcommerce.Producto.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gpus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private Integer memoriaGb;

    @Column(nullable = false)
    private Integer largoMm;

    @Column(nullable = false)
    private Integer tdpWatts;

}
