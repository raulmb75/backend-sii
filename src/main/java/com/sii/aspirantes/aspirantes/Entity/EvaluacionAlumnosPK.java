package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluacionAlumnosPK implements Serializable {

    @Column(name = "periodo", length = 5, nullable = false)
    private String periodo;
    @Column(name = "no_de_control", length = 9, nullable = false)
    private String noControl;
    @Column(name = "materia", length = 40, nullable = false)
    private String materia;
    @Column(name = "consecutivo", nullable = false)
    private int consecutivo;
}
