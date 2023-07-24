package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluacionAlumnos {

    @EmbeddedId
    protected EvaluacionAlumnosPK evaluacionAlumnosPK;
    @Column(name = "grupo")
    private int grupo;
    @Column(name = "rfc", length = 14)
    private String docente;
    @Column(name = "clave_area", length = 20)
    private String departamento;
    @Column(name = "encuesta")
    private String encuesta; // A - D - I
    @Column(name = "respuesta")
    private String respuestas;
    @Column(name = "resp_abierta")
    private String respAbierta;
    @Column(name = "usuario", length = 9)
    private String usuario;
    @Column(name = "fecha_hora_evaluacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEvaluacion;


}
