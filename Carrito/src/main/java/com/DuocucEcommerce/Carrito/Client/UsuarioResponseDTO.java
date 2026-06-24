package com.DuocucEcommerce.Carrito.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta entregada por la API para usuario.")
public class UsuarioResponseDTO { private Integer id; private String nombre; private String email; }

