package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Estudiante;
import com.sii.aspirantes.aspirantes.Repository.EstudianteRepository;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasAEvaluar;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> buscarTodosEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Estudiante buscarEstudiantePorNoDeControl(String noControl){
        Estudiante estudiante = estudianteRepository.findById(noControl)
                .orElseThrow(()->
                        new EntityNotFoundException("Estudiante con N.C. " + noControl + " no encontrado"));

        return estudiante;
    }

    public Estudiante guardarDatosEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarDatosDelEstudiante(String noControl, Estudiante estudiante){
        Estudiante estudianteRegistrado = estudianteRepository.findById(noControl).orElse(null);

        if(estudianteRegistrado != null){
            return estudianteRepository.save(estudiante);
        }

        throw new EntityNotFoundException("El alumno no está registrado");
    }

    public void eliminarEstudiante(String noControl){
        estudianteRepository.deleteById(noControl);
    }

    public List<Estudiante> buscarEstudiantesPorCarrera(Integer reticula){
        return estudianteRepository.buscarEstudiantesPorCarrera(reticula);
    }

    public List<MateriasAEvaluar> obtenerMateriasParaEvaluar(String periodo,
                                                             String noDeControl){
        return estudianteRepository.obtenerMateriasParaEvaluar(periodo, noDeControl);
    }

}
