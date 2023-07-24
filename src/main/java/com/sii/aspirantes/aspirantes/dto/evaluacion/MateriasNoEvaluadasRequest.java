package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MateriasNoEvaluadasRequest {

    private String materia;
    private String descripcionOmision;
    private Integer grupo;
}
