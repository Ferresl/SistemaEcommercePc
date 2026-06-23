package DuocucEcommerce.Pedido.Client;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Respuesta entregada por la API para producto.")
public class ProductoResponseDTO { 
    
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id; 
    
    @Schema(description = "Nombre comercial del producto.", example = "Procesador Ryzen 5 7600")
    private String nombre; 
    
    @Schema(description = "Precio del producto.", example = "249990")
    private BigDecimal precio; }
