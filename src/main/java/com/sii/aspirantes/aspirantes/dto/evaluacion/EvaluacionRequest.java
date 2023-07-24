package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EvaluacionRequest {

    private String departamento;
    private String docente;
    private String encuesta;
    private Integer consecutivo;
    private String materia;
    private String noControl;
    private String periodo;
    private Date fechaHoraEvaluacion;
    private Integer grupo;
    private String respAbierta;
    private String respuestas;
    private String usuario;
}
