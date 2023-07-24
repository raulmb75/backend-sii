package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class GestionCursosDTO {
    private String clave;
    private String nombreCurso;
    private String claveArea;
    private Date fechaInicio;
    private Date fechaFin;
    private Set<InstructorDTO> instructores;;
    private int horas;
    private String horario;
    private int cupo;
    private String lugar;
}
