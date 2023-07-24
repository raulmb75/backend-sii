package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.SeleccionMaterias;
import com.sii.aspirantes.aspirantes.Entity.SeleccionMateriasPK;
import com.sii.aspirantes.aspirantes.Repository.SeleccionMateriasRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SeleccionMateriasService {

    private final SeleccionMateriasRepository seleccionMateriasRepository;

    public SeleccionMateriasService(SeleccionMateriasRepository seleccionMateriasRepository) {
        this.seleccionMateriasRepository = seleccionMateriasRepository;
    }

    public List<SeleccionMaterias> buscarTodo(){
        return seleccionMateriasRepository.findAll();
    }

    public SeleccionMaterias buscarPorId(SeleccionMateriasPK id){
        SeleccionMaterias seleccionMaterias = seleccionMateriasRepository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Registro " + id + " no encontrado"));

        return seleccionMaterias;
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorPeriodo(String periodo){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorPeriodo(periodo);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorNoControl(String noControl){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorNoControl(noControl);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorMateria(String materia){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorMateria(materia);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorMateriaGrupo(String materia, String grupo){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorMateriaGrupo(materia, grupo);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorTipoEvaluacion(String tipoEvaluacion){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorTipoEvaluacion(tipoEvaluacion);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorCalificacion(Double calificacion1, Double calificacion2){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorCalificacion(calificacion1, calificacion2);
    }

    public List<SeleccionMaterias> buscarSeleccionMateriasPorStatusSeleccion(Character estatus){
        return seleccionMateriasRepository.buscarSeleccionMateriasPorStatusSeleccion(estatus);
    }
}
