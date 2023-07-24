package com.sii.aspirantes.aspirantes.dto;

import com.sii.aspirantes.aspirantes.Entity.Carrera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AulaExamenDTO {

    private String aula;
    private Short capacidad;
    private Short disponibilidad;
    private Short asignados;
    private String carrera;
}
