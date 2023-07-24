package com.sii.aspirantes.aspirantes.dto.evaluacion;

public interface IEvaluacionPorDocente {

    String getClaveGrupo();
    Integer getGrupo();
    String getNombreMateria();
    Integer getNoAlumnos();
    Integer getNoAlumnosEvaluaron();
    Float getPorcentajeEvaluacion();
}
