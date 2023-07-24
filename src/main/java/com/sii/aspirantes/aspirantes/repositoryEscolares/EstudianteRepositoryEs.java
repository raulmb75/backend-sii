package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AlumnosInscritosDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.EstudianteEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepositoryEs extends JpaRepository <EstudianteEs, String> {

  @Query(value = "SELECT * FROM estudiante WHERE reticula = ?1 AND periodo_ingreso_it= ?2  AND no_de_control IsNull", nativeQuery = true)
  public List<EstudianteEs> buscarEstudiantesAsignarNoControl(Integer reticula, String periodoIngresoIt);

  EstudianteEs findByNoDeControl(String noDeControl);

  //List<Estudiante> findByCarera(Integer reticula);

  @Query("FROM Estudiante e WHERE e.reticula.reticula = ?1")
  List<EstudianteEs> buscarEstudiantesPorCarrera(Integer reticula);

  @Query("FROM Estudiante e WHERE e.periodoIngresoIt.periodo = ?1")
  List<EstudianteEs> buscarEstudiantesPorPeriodo(String periodo);


  //buscar estudiantes inscritos por carrera y periodo en orden alfabetico
  //List<Estudiante> findByReticulaAndPeriodo(String degree, String enrollmentPeriod);


  @Query(value = "SELECT e.no_de_control AS noDeControl, e.nombre_alumno AS nombreAlumno, e.apellido_paterno AS apellidoPaterno, " +
    "e.apellido_materno AS apelliodMaterno, e.sexo, e.creditos_aprobados AS creditosAprobados, e.creditos_aprobados * 100 / e.creditos_cursados AS porcentajeAprobado," +
    " e.creditos_cursados AS creditosCursados, "+
    "e.creditos_cursados * 100 / 260 AS porcentajeCursado, "+
    "e.promedio_aritmetico_acumulado AS promedioAritmeticoAcumulado  FROM estudiante AS e " +
    "WHERE e.reticula = ?1 AND e.periodo_ingreso_it = ?2 " +
    "ORDER BY e.nombre_alumno ASC ", nativeQuery = true)
  List<AlumnosInscritosDTO> buscarEstudiantesInscritos(Integer reticula, String periodo);


}
