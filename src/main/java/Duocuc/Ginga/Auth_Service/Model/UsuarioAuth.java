package Duocuc.Ginga.Auth_Service.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAuth {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Email(message="Tiene que cumplir con la validacion de un email")
    @Column(nullable=false)
    @NotBlank(message="El campo email no puede quedar en blanco")
    private String email;


    @Column(nullable=false)
    @NotBlank(message="Este campo password no puede quedar en blanco")
    private String password;

    @Column(nullable=false)
    @NotBlank(message="Este campo rol no puede quedar en blanco")
    private String rol;


}
