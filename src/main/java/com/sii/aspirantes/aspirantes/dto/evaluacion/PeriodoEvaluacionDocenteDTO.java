package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoEvaluacionDocenteDTO {

    private String periodo;
    private Date fechaInicial;
    private Date fechaTermino;
}
