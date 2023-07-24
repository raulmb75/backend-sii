package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MateriasNoEvaluadasDTO {

    private String materia;
    private String nombreMateria;
    private Character evaluacion;
    private String descripcionOmision;
    private String grupo;
}
