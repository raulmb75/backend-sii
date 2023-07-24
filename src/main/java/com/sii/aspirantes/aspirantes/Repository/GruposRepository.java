package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.dto.GruposMateriasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GruposRepository extends JpaRepository<Grupos, Integer> {

    //Buscar por materia
    @Query(value = "SELECT g.* FROM grupos g " +
            "INNER JOIN materias_carreras mc ON g.id_materia_carrera = mc.id_materia_carrera " +
            "INNER JOIN materia m ON mc.materia = m.materia " +
            "WHERE trim(m.materia) = trim(?1)", nativeQuery = true)
    List<Grupos> buscarGruposPorMateria(String materia);
    //Buscar por carrera
    @Query(value = "FROM Grupos g WHERE g.reticula.reticula = ?1 AND g.periodo.periodo = ?2")
    List<Grupos> buscarGruposPorCarrera(Integer reticula, String periodo);

    //Buscar por periodo
    @Query(value = "FROM Grupos g WHERE g.periodo.periodo = ?1")
    List<Grupos> buscarGruposPorPeriodo(String periodo);

    //Buscar por personal
    @Query(value = "FROM Grupos g WHERE g.rfc.rfc = ?1")
    List<Grupos> buscarGruposPorDocente(String rfc);

    @Query(value = "FROM Grupos g WHERE g.rfc.rfc = ?1 " +
            "AND g.periodo.periodo = ?2")
    List<Grupos> buscarGruposPorDocentePeriodo(String rfc, String periodo);

    //Buscar por materia y RFC
    @Query(value = "FROM Grupos g WHERE g.materia = ?1 " +
            "AND g.rfc.rfc = ?2")
    List<Grupos> buscarGruposPorDocenteMateria(String materia, String rfc);

    @Query(value = "SELECT SUM(alumnos_inscritos) FROM grupos WHERE rfc = ?1 " +
            "AND periodo = ?2", nativeQuery = true)
    Integer contarGruposPorDocentePeriodo(String rfc, String periodo);

    @Query(value = "FROM Grupos g " +
            "WHERE g.periodo.periodo = ?1 " +
            "AND g.idMateriaCarrera.materia.claveArea.claveArea = ?2")
    List<Grupos> buscarGruposPorDepartamento(String periodo, String claveArea);

    @Query(value = "SELECT m.materia, m.nombre_completo_materia as nombreMateria, g.grupo " +
            "FROM grupos g " +
            "INNER JOIN materias_carreras mc ON g.id_materia_carrera = mc.id_materia_carrera " +
            "INNER JOIN materia m ON mc.materia = m.materia " +
            "WHERE g.reticula = ?1", nativeQuery = true)
    List<GruposMateriasDTO> buscarGruposConMateria(Integer reticula);

    @Query(value = "SELECT g.* FROM grupos g " +
            "INNER JOIN materias_carreras mc ON g.id_materia_carrera = mc.id_materia_carrera " +
            "INNER JOIN materia m ON mc.materia = m.materia " +
            "WHERE trim(m.materia) = trim(?1) AND g.grupo = ?2", nativeQuery = true)
    Optional<Grupos> buscarGrupoConMateria(String materia, Integer grupo);
}
