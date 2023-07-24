package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Materia;
import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import com.sii.aspirantes.aspirantes.Service.MateriaService;
import com.sii.aspirantes.aspirantes.Service.MateriasCarrerasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// No implementado por ningún rol (DDA - ASP)
@RestController
@RequestMapping(value = "/api/materias")
public class MateriaController {

    private final MateriaService materiaService;
    private final MateriasCarrerasService materiasCarrerasService;

    public MateriaController(MateriaService materiaService, MateriasCarrerasService materiasCarrerasService) {
        this.materiaService = materiaService;
        this.materiasCarrerasService = materiasCarrerasService;
    }

    @GetMapping
    public List<Materia> mostrarTodasMaterias(){
        return materiaService.buscarTodasMaterias();
    }

    @GetMapping(value = "/{claveMateria}")
    public Materia buscarMateriaPorClave(@PathVariable String claveMateria){
        return materiaService.buscarMateriaPorClave(claveMateria);
    }

    @PostMapping
    public ResponseEntity<Materia> registrarMateria(@RequestBody Materia materia){
        return new ResponseEntity<>(materiaService.registrarMateria(materia), HttpStatus.OK);
    }

    @PutMapping(value = "/{claveMateria}")
    public ResponseEntity<Materia> actualizarDatosMateria(@PathVariable String claveMateria,
                                                          @RequestBody Materia materia){
        return new ResponseEntity<>(materiaService.actualizarDatosMateria(claveMateria, materia), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{claveMateria}")
    public void eliminarMateria(@PathVariable String claveMateria){
        materiaService.eliminarMateria(claveMateria);
    }

    //Buscar por nivel escolar
    @GetMapping(value = "/nivel-escolar/{nivelEscolar}")
    public List<Materia> mostrarMateriaPorNivelEscolar(@PathVariable Character nivelEscolar){
        return materiaService.buscarMateriaPorNivelEscolar(nivelEscolar);
    }

    //Buscar por clave de área
    @GetMapping(value = "/area/{claveArea}")
    public List<Materia> mostrarMateriaPorDepartamento(@PathVariable String claveArea){
        return materiaService.buscarMateriaPorDepartamento(claveArea);
    }

    //Buscar por tipo de matera
    @GetMapping(value = "/tipo/{tipoMateria}")
    public List<Materia> mostrarMateriaPorTipo(@PathVariable Integer tipoMateria){
        return materiaService.buscarMateriaPorTipo(tipoMateria);
    }

}
