package com.sii.aspirantes.aspirantes.dto.aspirante;
//Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SeleccionFichaAspiranteRequest{
    private Long noFicha;
    private Long noSolicitud;
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String carrera;
    private Integer reticula;
    private boolean aceptado;
    private String clavePreparatoria;
    private String nombrePreparatoria;
}
