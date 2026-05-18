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
@Table(name = "gabinetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gabinete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer productoId;

    @Column(nullable = false)
    private String formatoSoportado;

    @Column(nullable = false)
    private Integer largoMaxGpuMm;

}
