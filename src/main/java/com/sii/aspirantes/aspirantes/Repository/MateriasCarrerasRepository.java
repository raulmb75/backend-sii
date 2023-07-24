package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriasCarrerasRepository extends JpaRepository<MateriasCarreras, Integer> {

    @Query(value = "FROM MateriasCarreras m WHERE m.reticula.reticula = ?1")
    List<MateriasCarreras> obtenerMateriasPorCarrera(Integer reticula);

    @Query(value = "FROM MateriasCarreras m " +
            "WHERE m.materia.claveArea.claveArea = ?1 " +
            "ORDER BY m.materia.materia ASC")
    List<MateriasCarreras> obtenerMateriasPorDepartamento(String claveArea);

    @Query(value = "FROM MateriasCarreras m " +
            "WHERE m.materia.claveArea.claveArea = ?1 " +
            "ORDER BY m.materia.materia ASC")
    List<MateriasCarreras> obtenerMateriasPorDepartamentoOrdenarPorRfc(String claveArea);

    @Query(value = "FROM MateriasCarreras m WHERE m.reticula.reticula = ?1 " +
            "AND m.semestreReticula = ?2")
    List<MateriasCarreras> obtenerMateriasPorCarreraSemestre(Integer reticula, Integer semestre);

    @Query(value = "FROM MateriasCarreras m WHERE m.materia.claveArea.claveArea = ?1 " +
            "AND m.semestreReticula = ?2")
    List<MateriasCarreras> obtenerMateriasPorDepartamentoSemestre(String claveArea, Integer semestre);


    @Query(value = "SELECT * FROM materias_carreras LIMIT 1", nativeQuery = true)
    List<MateriasCarreras> buscarMateriasCarreras();

}
