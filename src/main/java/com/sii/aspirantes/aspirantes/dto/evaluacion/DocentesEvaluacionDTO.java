package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocentesEvaluacionDTO{
    private Integer alumnosInscritos;
    private Integer alumnosEvaluaron;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String departamento;

}
