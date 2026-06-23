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
@Table(name = "cpus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private String socket;

    @Column(nullable = false)
    private Integer nucleos;

    @Column(nullable = false)
    private Integer hilos;

    @Column(nullable = false)
    private Integer tdpWatts;

}
