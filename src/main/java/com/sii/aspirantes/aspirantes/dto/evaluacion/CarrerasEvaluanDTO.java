package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarrerasEvaluanDTO {
    private Integer reticula;
    private String carrera;
    private String nombreCarrera;
    private Character evaluacion;
}
