package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.EvaluacionAlumnos;
import com.sii.aspirantes.aspirantes.dto.evaluacion.DocentesEvaluacionAlumnos;
import com.sii.aspirantes.aspirantes.dto.evaluacion.IEvaluacionPorDocente;
import com.sii.aspirantes.aspirantes.dto.evaluacion.IEvaluacionPorMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionAlumnosRepository extends JpaRepository<EvaluacionAlumnos, Long> {

    @Query(value = "FROM EvaluacionAlumnos WHERE periodo = ?1")
    public List<EvaluacionAlumnos> buscarPorPeriodo(String periodo);
    @Query(value = "FROM EvaluacionAlumnos WHERE docente = ?1")
    public List<EvaluacionAlumnos> buscarPorDocente(String docente);
    @Query(value = "FROM EvaluacionAlumnos WHERE departamento = ?1")
    public List<EvaluacionAlumnos> buscarPorDepartamento(String claveArea);
    @Query(value = "FROM EvaluacionAlumnos WHERE materia = ?1")
    public List<EvaluacionAlumnos> buscarPorClaveMateria(String claveMateria);

    //Buscar por docente y por materia
    @Query(value = "SELECT * FROM evaluacion_alumnos " +
            "WHERE trim(rfc) = trim(?1) " +
            "AND trim(materia) = trim(?2) " +
            "AND grupo = ?3 " +
            "AND periodo = ?4",
    nativeQuery = true)
    List<EvaluacionAlumnos> busquedaCompleta(String rfc, String claveMateria, Integer grupo, String periodo);

    @Query(value = "SELECT COUNT(*) FROM evaluacion_alumnos e WHERE e.rfc = ?1 " +
            "AND e.periodo = ?2", nativeQuery = true)
    Integer contarEvaluacionesPorDocentePeriodo(String rfc, String periodo);

    //Genera la tabla de los resultados de evaluación por docente
    @Query(value = "SELECT  trim(e.materia) AS claveGrupo, " +
            "e.grupo AS grupo, " +
            "m.nombre_completo_materia AS nombreMateria, " +
            "g.alumnos_inscritos AS noAlumnos, " +
            "COUNT(*) AS noAlumnosEvaluaron, " +
            "COUNT(*) * 100.0 / g.alumnos_inscritos AS porcentajeEvaluacion " +
            "FROM evaluacion_alumnos AS e " +
            "INNER JOIN grupos AS g " +
            "ON trim(e.materia) = trim(g.materia) and e.grupo = g.grupo " +
            "INNER JOIN materia AS m " +
            "ON trim(g.materia) = trim(m.materia) " +
            "WHERE e.periodo = ?1 AND e.rfc = ?2 " +
            "GROUP BY e.grupo, e.materia, m.nombre_completo_materia, g.alumnos_inscritos", nativeQuery = true)
    List<IEvaluacionPorDocente> datosEvaluacionDocentePorDocente(String periodo, String rfc);

    @Query(value = "SELECT e.rfc, " +
            "e.grupo, " +
            "m.nombre_completo_materia as nombreMateria, " +
            "g.alumnos_inscritos as noAlumnos, " +
            "COUNT(*) as noAlumnosEvaluaron, " +
            "COUNT(*) * 100.0 / g.alumnos_inscritos as porcentajeEvaluacion " +
            "FROM evaluacion_alumnos as e " +
            "INNER JOIN grupos as g ON trim(e.materia) = trim(g.materia) AND e.grupo = g.grupo " +
            "INNER JOIN materia as m ON trim(g.materia) = trim(m.materia) " +
            "WHERE e.periodo = ?1 AND trim(e.materia) = trim(?2) " +
            "GROUP BY e.materia, e.rfc, e.grupo, m.nombre_completo_materia, g.alumnos_inscritos",
            nativeQuery = true)
    List<IEvaluacionPorMateria> datosEvaluacionDocentePorMateria(String periodo, String materia);

    @Query(value = "SELECT distinct e.rfc, " +
            "p.nombre_empleado AS nombreEmpleado, " +
            "p.apellido_paterno AS apellidoPaterno, " +
            "p.apellido_materno AS apellidoMaterno, " +
            "p.clave_area AS claveArea, " +
            "trim(o.descripcion_area) AS descripcionArea," +
            "UPPER('si') as evaluado " +
            "FROM evaluacion_alumnos e " +
            "INNER JOIN personal p ON e.rfc = p.rfc " +
            "INNER JOIN organigrama o ON p.clave_area = o.clave_area " +
            "WHERE e.periodo = ?1 AND e.clave_area = ?2", nativeQuery = true)
    List<DocentesEvaluacionAlumnos> buscarDocenteEvaluadosPorArea(String periodo, String claveArea);

    @Query(value = "SELECT distinct e.rfc, " +
            "p.nombre_empleado AS nombreEmpleado, " +
            "p.apellido_paterno AS apellidoPaterno, " +
            "p.apellido_materno AS apellidoMaterno, " +
            "p.clave_area AS claveArea, " +
            "trim(o.descripcion_area) AS descripcionArea," +
            "upper('si') as evaluado " +
            "FROM evaluacion_alumnos e " +
            "INNER JOIN personal p ON e.rfc = p.rfc " +
            "INNER JOIN organigrama o ON p.clave_area = o.clave_area " +
            "WHERE e.periodo = ?1", nativeQuery = true)
    List<DocentesEvaluacionAlumnos> buscarDocenteEvaluados(String periodo);

}
