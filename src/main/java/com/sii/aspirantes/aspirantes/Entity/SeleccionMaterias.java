package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seleccion_materias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeleccionMaterias {

    @EmbeddedId
    protected SeleccionMateriasPK seleccionMateriasPK;
    @Column(name = "calificacion")
    private Double calificacion;
    @Column(name = "tipo_evaluacion")
    private String tipoEvaluacion;
    @Column(name = "repeticion")
    private Character repeticion;
    @Column(name = "nopresento")
    private Character nopresento;
    @Column(name = "status_seleccion")
    private Character statusSeleccion;
    @Column(name = "fecha_hora_seleccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSeleccion;
    @Column(name = "global")
    private Character global;

}
