package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.PeriodoEscolar;
import com.sii.aspirantes.aspirantes.Entity.Preguntas;
import com.sii.aspirantes.aspirantes.Entity.PreguntasPK;
import com.sii.aspirantes.aspirantes.Repository.PeriodoEscolarRepository;
import com.sii.aspirantes.aspirantes.Repository.PreguntasRepository;
import com.sii.aspirantes.aspirantes.dto.evaluacion.PreguntasDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class PreguntasService{

    private final PreguntasRepository preguntasRepository;
    private final PeriodoEscolarRepository periodoEscolarRepository;

    public PreguntasService(PreguntasRepository preguntasRepository, PeriodoEscolarRepository periodoEscolarRepository) {
        this.preguntasRepository = preguntasRepository;
        this.periodoEscolarRepository = periodoEscolarRepository;
    }

    public List<Preguntas> buscarPreguntas(String periodo) {
        PeriodoEscolar fechaEvaluacion = periodoEscolarRepository.findById(periodo)
                .orElseThrow(()->
                        new EntityNotFoundException("Periodo de evaluacion " + periodo + " no establecido"));

        Date fechaInicio = fechaEvaluacion.getInicioEncEstudiantil();
        Date fechaFinal = fechaEvaluacion.getFinEncEstudiantil();
        Date fechaActual = new Date();

        if(fechaActual.after(fechaInicio) && fechaActual.before(fechaFinal)){
            //Si la fecha actual se encuentra entre la fecha inicial y la fecha final
            return preguntasRepository.buscarPreguntasPorEncuesta('A');
        }

        throw new RuntimeException("El periodo de evaluación no está habilitado");
    }

    public Preguntas guardarPregunta(Preguntas pregunta){
        return preguntasRepository.save(pregunta);
    }

    public Preguntas buscarPreguntaPorId(PreguntasPK preguntaId){
        Preguntas pregunta = preguntasRepository.findById(preguntaId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Pregunta con id " + preguntaId + " no encontrada"));

        return pregunta;
    }

    public void eliminarPregunta(PreguntasPK preguntaId){
        preguntasRepository.deleteById(preguntaId);
    }

    //Buscar por aspecto
    public List<Preguntas> buscarPreguntasPorAspecto(Character aspecto){
        return preguntasRepository.buscarPreguntasPorAspecto(aspecto);
    }

    //Buscar por consecutivo
    public List<Preguntas> buscarPreguntasPorConsecutivo(Integer consecutivo){
        return preguntasRepository.buscarPreguntasPorConsecutivo(consecutivo);
    }

    //Buscar por encuesta
    public List<Preguntas> buscarPreguntasPorEncuesta(Character encuesta){
        return preguntasRepository.buscarPreguntasPorEncuesta(encuesta);
    }

    public List<Preguntas> buscarPreguntasPorEncuestaAspecto(Character encuesta, Character aspecto){
        return preguntasRepository.buscarPreguntasPorEncuestaAspecto(encuesta, aspecto);
    }
}
