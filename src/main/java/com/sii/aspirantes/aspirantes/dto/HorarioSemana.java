package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HorarioSemana {

    private Short dia;
    private Date horaInicial;
    private Date horaFinal;
    private String aula;
}
