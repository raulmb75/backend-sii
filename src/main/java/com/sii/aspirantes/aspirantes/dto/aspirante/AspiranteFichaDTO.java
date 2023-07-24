package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Lista de FICHAS de aspirantes por carrera y aula
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AspiranteFichaDTO implements Serializable {

    private Long noFicha;
    private Long noSolicitud;
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String aula;
    private String carrera;
    private String curp;
    private String telefono;
    private String correoElectronico;

    //Se podría agregar la escuela de procedencia, fecha de registro

}
