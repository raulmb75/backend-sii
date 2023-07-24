package com.sii.aspirantes.aspirantes.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carrera")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

    @Id
    @Column(name = "reticula")
    private Integer reticula;
    @Column(name = "carrera", length = 3,nullable = false)
    private String carrera;
    @Column(name = "nivel_escolar", nullable = false) //Debe ser una relación
    private Character nivelEscolar;
    @Column(name = "nombre_carrera", length = 150)
    private String nombreCarrera;
    @Column(name = "nombre_reducido", length = 25)
    private String nombreReducido;
    @Column(name = "siglas", length = 10)
    private String siglas;
    @Column(name = "carga_maxima", nullable = false)
    private int cargaMaxima;
    @Column(name = "carga_minima", nullable = false)
    private int cargaMinima;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_termino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;
    @Column(name = "creditos_totales", nullable = false)
    private Integer creditosTotales;
    @Column(name = "modalidad", nullable = false)
    private Character modalidad;
    @Column(name = "autorizacion" ,length = 20)
    private String autorizacion;
    @Column(name = "clave_oficial")
    private String claveficial;


}
