package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.SeleccionMaterias;
import com.sii.aspirantes.aspirantes.Service.SeleccionMateriasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//No implementado por ningún rol
@RestController
@RequestMapping(value = "/api/seleccion-materias")
public class SeleccionMateriasController {

    private final SeleccionMateriasService seleccionMateriasService;

    public SeleccionMateriasController(SeleccionMateriasService seleccionMateriasService) {
        this.seleccionMateriasService = seleccionMateriasService;
    }

    @GetMapping
    public List<SeleccionMaterias> mostrarSeleccionMaterias(){
        return seleccionMateriasService.buscarTodo();
    }

    @GetMapping(value = "/periodo/{periodo}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorPeriodo(@PathVariable String periodo){
        return seleccionMateriasService.buscarSeleccionMateriasPorPeriodo(periodo);
    }

    @GetMapping(value = "/alumno/{noControl}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorAlumno(@PathVariable String noControl){
        return seleccionMateriasService.buscarSeleccionMateriasPorNoControl(noControl);
    }

    @GetMapping(value = "/materia/{materia}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorMateria(@PathVariable String materia){
        return seleccionMateriasService.buscarSeleccionMateriasPorMateria(materia);
    }

    @GetMapping(value = "/tipoEval/{tipoEval}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorTipoEvaluacion(@PathVariable String tipoEvaluacion){
        return seleccionMateriasService.buscarSeleccionMateriasPorTipoEvaluacion(tipoEvaluacion);
    }

    @GetMapping(value = "/status/{status}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorTipoStatusSeleccion(@PathVariable Character status){
        return seleccionMateriasService.buscarSeleccionMateriasPorStatusSeleccion(status);
    }

    @GetMapping(value = "/grupo/{materia}/{grupo}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorMateriaGrupo(@PathVariable String materia, @PathVariable String grupo){
        return seleccionMateriasService.buscarSeleccionMateriasPorMateriaGrupo(materia, grupo);
    }

    @GetMapping(value = "/calificacion/{calificacion1}/{calificacion2}")
    public List<SeleccionMaterias> mostrarSeleccionMateriasPorCalificacion(@PathVariable Double calificacion1,
                                                                           @PathVariable Double calificacion2){
        return seleccionMateriasService.buscarSeleccionMateriasPorCalificacion(calificacion1, calificacion2);
    }
}
