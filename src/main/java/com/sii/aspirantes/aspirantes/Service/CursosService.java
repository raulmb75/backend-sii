package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Instructor;
import com.sii.aspirantes.aspirantes.Repository.InstructoresRepository;
import com.sii.aspirantes.aspirantes.dto.GestionCursosDTO;
import com.sii.aspirantes.aspirantes.dto.InstructorDTO;
import com.sii.aspirantes.aspirantes.Entity.Cursos;
import com.sii.aspirantes.aspirantes.Repository.CursosRepository;
import com.sii.aspirantes.aspirantes.dto.ListaCursosDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CursosService {

    private final CursosRepository cursosRepository;
    private final InstructoresRepository instructoresRepository;

    public CursosService(CursosRepository cursosRepository, InstructoresRepository instructoresRepository) {
        this.cursosRepository = cursosRepository;
        this.instructoresRepository = instructoresRepository;
    }

    public List<GestionCursosDTO> consultarListaCursos(String periodo, String rfc){

        List<GestionCursosDTO> listaGestionCursosDTO = new ArrayList<>();
        GestionCursosDTO gestionCursosDTO;
        List<Cursos> listaCursos;
        Instructor instructor = instructoresRepository.findById(rfc).orElse(null);

        if(Objects.nonNull(periodo) && Objects.nonNull(instructor)){
            listaCursos = cursosRepository.buscarCursosPorInstructor(periodo, rfc);
            System.out.println("Busca por filtro");
        }else{
            listaCursos = cursosRepository.findAll();
        }

        for(Cursos curso: listaCursos){

            gestionCursosDTO = GestionCursosDTO.builder()
                    .clave(curso.getClave())
                    .nombreCurso(curso.getNombreCurso())
                    .claveArea(curso.getClaveArea().getClaveArea())
                    .fechaInicio(curso.getFechaInicio())
                    .fechaFin(curso.getFechaFin())
                    .instructores(agregarInstructores(curso.getInstructores()))
                    .horario(curso.getHorario())
                    .horas(curso.getHoras())
                    .cupo(curso.getCupo())
                    .lugar(curso.getLugar())
                    .build();

            listaGestionCursosDTO.add(gestionCursosDTO);
        }

        return listaGestionCursosDTO;
    }

    public Cursos consultarCurso(String clave){
        Cursos curso =  cursosRepository.findById(clave)
                .orElseThrow(()->
                        new EntityNotFoundException("Curso con clave " + clave + " no encontrado."));

        return curso;
    }

    public Cursos registrarNuevoCurso(Cursos cursos){
        //Hacer un DTO para guardar los instructores
        return cursosRepository.save(cursos);
    }

    public Cursos actualizarInfoCurso(String clave, Cursos curso){
        //Probar este método
        Cursos cursoRegistrado = cursosRepository.findById(clave).orElse(null);

        if(cursoRegistrado != null){
            cursoRegistrado = curso;
            cursoRegistrado.setClave(clave);
            return cursosRepository.save(cursoRegistrado);
        }

        return null;
    }

    public void eliminarCurso(String clave){
        cursosRepository.deleteById(clave);
    }

    public List<ListaCursosDTO> listaGestionCursos(String periodo){
        return cursosRepository.listaGestionCursos(periodo);
    }








    private Set<InstructorDTO> agregarInstructores(Set<Instructor> instructores){
        Set<InstructorDTO> instructorDTOS = instructores.stream().map(instructor ->
                InstructorDTO.builder()
                .nombre(instructor.getNombre())
                .apellidoPaterno(instructor.getApellidoPaterno())
                .apellidoMaterno(instructor.getApellidoMaterno())
                .build()).collect(Collectors.toSet());


        return instructorDTOS;
    }
}
