package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.Entity.MateriasNoEvaluadas;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriasNoEvaluadasRepository extends JpaRepository<MateriasNoEvaluadas, Long> {

    //Materias agregadas a la lista de no evaluadas
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasDTO(" +
            "m.grupo.idMateriaCarrera.materia.materia, m.grupo.idMateriaCarrera.materia.nombreCompletoMateria, m.evaluacion, m.descripcionOmision, m.grupo.grupo) " +
            "FROM MateriasNoEvaluadas m")
    List<MateriasNoEvaluadasDTO> buscarMateriasNoEvaluadas();

    @Query(value = "FROM MateriasNoEvaluadas m WHERE m.grupo.idMateriaCarrera.materia.materia = ?1")
    Optional<MateriasNoEvaluadas> buscarPorMateria(String materia);

    @Query(value = "FROM MateriasNoEvaluadas WHERE grupo = ?1")
    Optional<MateriasNoEvaluadas> buscarPorGrupo(Grupos grupo);

}
