package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GruposCarreraDTO {
    //Materia
    //Grupo
    //Excl car
    //Capacidad
    //Docente (RFC y nombre)
    //horario: lunes - domingo
    //Paralelo de

    private String materia;
    private String nombreMateria;
    private String grupo;
    private Integer exclCar;
    private Integer capacidad;
    private Integer alumnosInsgritos;
    private String rfc;
    private String nombreDocente;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private List<HorarioSemana> horarioSemana;

}


