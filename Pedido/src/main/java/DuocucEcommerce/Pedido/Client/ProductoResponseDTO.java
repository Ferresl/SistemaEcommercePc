package DuocucEcommerce.Pedido.Client;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductoResponseDTO { 
    
    private Integer id; 
    
    private String nombre; 
    
    private BigDecimal precio; }