package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_evaluacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiposEvaluacion {

    @Id
    private Character encuesta;
    @Column(length = 5)
    private String tipo;
    @Column(length = 50)
    private String descripcion;
}
