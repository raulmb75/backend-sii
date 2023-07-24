package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "valor_opciones_evaluacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValorOpcionesEvaluacion {

    @Id
    @Basic(optional = false)
    private Character opcion;
    @Column(nullable = false)
    private Short valor;
}
