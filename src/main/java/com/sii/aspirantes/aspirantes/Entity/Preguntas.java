package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preguntas {

    @EmbeddedId
    protected PreguntasPK preguntasPK;
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @Column(name = "resp_val")
    private int respVal;
    @Column(name = "respuestas")
    private String respuestas;
}
