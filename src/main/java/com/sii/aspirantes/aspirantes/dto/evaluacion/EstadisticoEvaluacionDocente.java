package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadisticoEvaluacionDocente {

    private String docente;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private List<IEvaluacionPorDocente> grupos;
}
