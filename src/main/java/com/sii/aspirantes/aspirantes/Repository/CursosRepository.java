package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Cursos;
import com.sii.aspirantes.aspirantes.Entity.Instructor;
import com.sii.aspirantes.aspirantes.dto.ListaCursosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, String> {

    //Buscar curso por rfc y periodo
    /*@Query(value = "FROM Cursos c " +
            "WHERE c.periodo.periodo = ?1 AND c.instructores = ?2")
    List<Cursos> buscarCursosPorInstructor(String periodo, Instructor instructor);*/

    @Query(value = "SELECT c.* FROM cursos c " +
            "INNER JOIN cursos_instructores ci ON c.clave = ci.clave " +
            "INNER JOIN instructor i ON ci.rfc = i.rfc " +
            "WHERE c.periodo = ?1 AND i.rfc = ?2", nativeQuery = true)
    List<Cursos> buscarCursosPorInstructor(String periodo, String rfc);

    //Gestión de Listas de Cursos
    @Query(value = "SELECT c.clave, c.nombre_curso AS nombreCurso " +
            "FROM cursos c " +
            "WHERE c.periodo = ?1", nativeQuery = true)
    List<ListaCursosDTO> listaGestionCursos(String periodo);

}
