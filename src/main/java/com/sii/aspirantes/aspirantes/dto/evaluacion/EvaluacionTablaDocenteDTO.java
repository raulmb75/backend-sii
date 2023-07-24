package com.sii.aspirantes.aspirantes.dto.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluacionTablaDocenteDTO {
    private String aspecto;
    private float puntaje;
    private String calificacion;
    private String calificacionGrafica; // Debería omitirse
}
