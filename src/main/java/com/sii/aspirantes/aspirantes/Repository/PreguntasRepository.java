package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Preguntas;
import com.sii.aspirantes.aspirantes.Entity.PreguntasPK;
import com.sii.aspirantes.aspirantes.dto.evaluacion.PreguntasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, PreguntasPK> {

    //Buscar por aspecto
    @Query(value = "FROM Preguntas p WHERE p.preguntasPK.aspecto = ?1")
    List<Preguntas> buscarPreguntasPorAspecto(Character aspecto);
    //Buscar por consecutivo
    @Query(value = "FROM Preguntas p WHERE p.preguntasPK.consecutivo = ?1")
    List<Preguntas> buscarPreguntasPorConsecutivo(Integer consecutivo);
    //Buscar por encuesta
    @Query(value = "FROM Preguntas p " +
            "WHERE p.preguntasPK.encuesta = ?1 " +
            "ORDER BY p.preguntasPK.noPregunta ASC")
    List<Preguntas> buscarPreguntasPorEncuesta(Character encuesta);
    //Buscar por tipo de encuesta y por aspecto
    @Query(value = "FROM Preguntas p WHERE p.preguntasPK.encuesta = ?1 " +
            "AND p.preguntasPK.aspecto = ?2")
    List<Preguntas> buscarPreguntasPorEncuestaAspecto(Character encuesta, Character aspecto);

    //buscar preguntas con aspectos
    @Query(value = "SELECT a.descripcion AS aspecto, p.pregunta " +
            "FROM preguntas p " +
            "INNER JOIN aspectos a " +
            "ON p.aspecto = a.aspecto " +
            "WHERE p.encuesta = ?1", nativeQuery = true)
    List<PreguntasDTO> buscarPreguntasConAspectos(Character encuesta);

}
