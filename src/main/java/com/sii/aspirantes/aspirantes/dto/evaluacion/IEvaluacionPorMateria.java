package com.sii.aspirantes.aspirantes.dto.evaluacion;

public interface IEvaluacionPorMateria {
    String getRfc();
    Integer getGrupo();
    String getNombreMateria();
    Integer getNoAlumnos();
    Integer getNoAlumnosEvaluaron();
    Float getPorcentajeEvaluacion();
}
