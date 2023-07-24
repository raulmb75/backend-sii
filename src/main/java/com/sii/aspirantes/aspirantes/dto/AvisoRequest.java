package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AvisoRequest {
    private String titulo;
    private String contenido;
    private String departamento;
    private String usuario;
    private boolean visible;
    private Date fechaInicio;
    private Date fechaVigencia;
    private String seccion;
}
