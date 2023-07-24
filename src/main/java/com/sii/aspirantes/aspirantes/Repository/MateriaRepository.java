package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Materia;
import com.sii.aspirantes.aspirantes.dto.ListaMateriasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, String> {

    //Buscar por nivel escolar
    @Query(value = "FROM Materia m WHERE m.nivelEscolar.nivelEscolar = ?1")
    List<Materia> buscarMateriaPorNivelEscolar(Character nivelEscolar);

    //Buscar por clave de área
    @Query(value = "FROM Materia m WHERE m.claveArea.claveArea = ?1")
    List<Materia> buscarMateriaPorDepartamento(String claveArea);

    //Buscar por tipo de matera
    @Query(value = "FROM Materia m WHERE m.tipoMateria.tipoMateria = ?1")
    List<Materia> buscarMateriaPorTipo(Integer tipoMateria);

    //Lista desplegable - Materias por reticula
    @Query(value = "SELECT m.materia, m.nombre_completo_materia AS nombreCompletoMateria " +
            "FROM materia m " +
            "INNER JOIN materias_carreras mc ON m.materia = mc.materia " +
            "INNER JOIN carrera c ON mc.reticula = c.reticula " +
            "WHERE c.reticula = ?1", nativeQuery = true)
    List<ListaMateriasDTO> buscarMateriaPorReticula(Integer reticula);
}
