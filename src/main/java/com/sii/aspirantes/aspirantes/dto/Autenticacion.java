package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autenticacion {

    private String tipo_usuario;
    private String usuario;
    private String contrasena;

}
