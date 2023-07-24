package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Estudiante;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasAEvaluar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    //Buscar por carrera
    @Query("FROM Estudiante e WHERE e.reticula.reticula = ?1")
    List<Estudiante> buscarEstudiantesPorCarrera(Integer reticula);

    @Query(value = "SELECT e.no_de_control as noDeControl, " +
            "sm.materia, " +
            "sm.grupo, " +
            "sm.periodo, " +
            "g.rfc, " +
            "p.nombre_empleado as nombreEmpleado, " +
            "p.apellido_paterno as apellidoPaterno, " +
            "p.apellido_materno as apellidoMaterno " +
            "FROM estudiante e " +
            "INNER JOIN seleccion_materias sm ON e.no_de_control = sm.no_de_control " +
            "INNER JOIN grupos g ON sm.grupo = g.grupo AND sm.materia = g.materia " +
            "INNER JOIN personal p ON g.rfc = p.rfc " +
            "WHERE sm.periodo = ?1 AND e.no_de_control = ?2", nativeQuery = true)
    List<MateriasAEvaluar> obtenerMateriasParaEvaluar(String periodo, String noDeControl);
}
