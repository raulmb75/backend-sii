package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.PeriodoEscolar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class PeriodoEvaluacionDocenteRepositoryTest {

    @Autowired
    PeriodoEscolarRepository periodoEscolarRepository;
    @Test
    public void guardarFechaEvaluacionDocente(){
        PeriodoEscolar periodoEvaluacionDocente = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);
        Date fechaInicio = new Date();
        fechaInicio.setDate(1);
        fechaInicio.setMonth(3);
        fechaInicio.setYear(2023 - 1900);
        System.out.println("fechaInicio = " + fechaInicio);

        Date fechaTermino = new Date();
        fechaTermino.setDate(1);
        fechaTermino.setMonth(4);
        fechaTermino.setYear(2023 - 1900);
        System.out.println("fechaTermino = " + fechaTermino);

        //periodoEvaluacionDocente.setPeriodo("20231");
        periodoEvaluacionDocente.setInicioEncEstudiantil(fechaInicio);
        periodoEvaluacionDocente.setFinEncEstudiantil(fechaTermino);

        periodoEscolarRepository.save(periodoEvaluacionDocente);
    }

    @Test
    public void mostrarPeriodoEvaluacionDocente(){
        PeriodoEscolar periodoEvaluacionDocente = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);

        System.out.println("Fecha inicio evaluación docente: " + periodoEvaluacionDocente.getInicioEncEstudiantil());
        System.out.println("Fecha fin evaluación docente: " + periodoEvaluacionDocente.getFinEncEstudiantil());
    }

}