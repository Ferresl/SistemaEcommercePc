package DuocucEcommerce.Pedido.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionCreateDTO {
     
    private Integer usuarioId; 
    
    private String titulo; 
    
    private String mensaje; 
    
    private String tipo; 
    
    private Boolean leida; }

