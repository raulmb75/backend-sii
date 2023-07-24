package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Cursos;
import com.sii.aspirantes.aspirantes.Entity.Instructor;
import com.sii.aspirantes.aspirantes.Entity.Organigrama;
import com.sii.aspirantes.aspirantes.Entity.PeriodoEscolar;
import com.sii.aspirantes.aspirantes.Repository.CursosRepository;
import com.sii.aspirantes.aspirantes.Repository.InstructoresRepository;
import com.sii.aspirantes.aspirantes.Repository.OrganigramaRepository;
import com.sii.aspirantes.aspirantes.Repository.PeriodoEscolarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CursosServiceTest {

    @Autowired
    private CursosService cursosService;
    @Autowired
    private PeriodoEscolarRepository periodoEscolarRepository;
    @Autowired
    private OrganigramaRepository organigramaRepository;
    @Autowired
    InstructoresRepository instructoresRepository;
    @Autowired
    private CursosRepository cursosRepository;

    @Test
    public void rgistrarCurso(){
        Set<Instructor> listaInstructores = new HashSet<>();
        List<Instructor> instructores =  instructoresRepository.findAll();
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);
        Organigrama organigrama = organigramaRepository.findById("110200").orElse(null);

        instructores.forEach(instructor -> listaInstructores.add(instructor));

        Cursos curso = Cursos.builder()
                .clave("CNPC0105")
                .periodo(periodoEscolar)
                .nombreCurso("CURSO DE EJEMPLO")
                .fechaRegistroCurso(new Date())
                .fechaInicio(new Date())
                .fechaFin(new Date())
                .cupo(15)
                .horas(30)
                .instructores(listaInstructores)
                .claveArea(organigrama)
                .build();

    cursosService.registrarNuevoCurso(curso);
    }

    @Test
    public void buscarCursoPorInstructor(){

        //Instructor instructor = instructoresRepository.findById("AABDFFV2014").orElse(null);
        String rfc = "AABDFFV2014";
        List<Cursos> cursos = cursosRepository.buscarCursosPorInstructor("20231", rfc);

        if(cursos.isEmpty() || Objects.isNull(cursos)){
            System.out.println("No hay cursos disponibles para este usuario o el usuario ingresado no existe");
            System.out.println("Usuario ingresado " + rfc);
        }

        cursos.forEach(curso -> System.out.println("curso = " + curso.getClave()));
    }
}