package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Preguntas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PreguntasRepositoryTest {

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Test
    void buscarPreguntasPorAspecto() {
        List<Preguntas> preguntas = preguntasRepository.buscarPreguntasPorAspecto('A');

        for(Preguntas pregunta: preguntas){
            System.out.println(pregunta.getPreguntasPK());
        }
    }

    @Test
    void buscarPreguntasPorConsecutivo() {
    }

    @Test
    void buscarPreguntasPorEncuesta() {
        List<Preguntas> preguntas = preguntasRepository.buscarPreguntasPorEncuesta('A');

        for(Preguntas pregunta: preguntas){
            System.out.println(pregunta.getPreguntasPK());
        }
    }
}