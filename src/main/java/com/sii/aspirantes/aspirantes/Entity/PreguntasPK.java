package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "aspecto")
    private Character aspecto;
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @Basic(optional = false)
    @Column(name = "encuesta")
    private Character encuesta;
    @Basic(optional = false)
    @Column(name = "no_pregunta")
    private Integer noPregunta;
}
