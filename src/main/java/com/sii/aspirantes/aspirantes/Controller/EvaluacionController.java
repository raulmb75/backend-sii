package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.Service.EvaluacionAluService;
import com.sii.aspirantes.aspirantes.Service.ValorOpcionesEvaluacionService;
import com.sii.aspirantes.aspirantes.dto.evaluacion.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dda/evaluacion")
public class EvaluacionController {

    private final EvaluacionAluService evaluacionAluService;
    private final ValorOpcionesEvaluacionService valorOpcionesEvaluacionService;

    public EvaluacionController(EvaluacionAluService evaluacionAluService, ValorOpcionesEvaluacionService valorOpcionesEvaluacionService) {
        this.evaluacionAluService = evaluacionAluService;
        this.valorOpcionesEvaluacionService = valorOpcionesEvaluacionService;
    }

    /*
    MOSTRAR LAS EVALUACIONES DEL ALUMNO
     */

    @GetMapping
    public List<EvaluacionAlumnos> mostrarEvaluacion(){
        return evaluacionAluService.mostrarEvaluacion();
    }

    @GetMapping("/{periodo}")
    public List<EvaluacionAlumnos> mostrarEvaluacionPorPeriodo(@PathVariable String periodo){
        return evaluacionAluService.mostrarEvaluacionPorPeriodo(periodo);
    }

    @GetMapping("/por_docente")
    public List<EvaluacionAlumnos> mostrarEvaluacionPorDocente(@RequestParam String periodo,
                                                               @RequestParam String docente){
        return evaluacionAluService.mostrarEvaluacionPorDocente(docente);
    }

    @GetMapping("/por_departamento")
    public List<EvaluacionAlumnos> mostrarEvaluacionPorDepartamento(@RequestParam String periodo,
                                                                    @PathVariable String claveArea){
        return evaluacionAluService.mostrarEvaluacionPorDepartamento(claveArea);
    }

    @GetMapping("/por_materia")
    public List<EvaluacionAlumnos> mostrarEvaluacionPorMateria(@RequestParam String periodo,
                                                               @RequestParam String materia){
        return evaluacionAluService.mostrarEvaluacionPorMateria(materia);
    }

    @GetMapping("/docentes_evaluados")
    public List<DocentesEvaluacionAlumnos> obtenerDocentesEvaluacionAlumnos(@RequestParam String periodo){
        return evaluacionAluService.obtenerDocentesEvaluacionAlumnos(periodo);
    }

    @GetMapping("/docentes_evaluados/por_departamento")
    public List<DocentesEvaluacionAlumnos> obtenerDocentesEvaluacionAlumnosPorDepartamento(@RequestParam String periodo,
                                                                                           @RequestParam String claveArea){
        return evaluacionAluService.obtenerDocentesEvaluacionAlumnosPorDepartamento(periodo, claveArea);
    }

    @PostMapping
    public ResponseEntity<EvaluacionAlumnos> guardarEvaluacionIndividual(@RequestBody EvaluacionRequest evaluacionIndividual){
        return new ResponseEntity<>(evaluacionAluService.guardarEvaluacionIndividual(evaluacionIndividual), HttpStatus.OK);
    }

    @PostMapping("/por_alumnos")
    public ResponseEntity<List<EvaluacionAlumnos>> guardarEvaluacionCompleta(@RequestBody List<EvaluacionRequest> listaEvaluacion){
        return new ResponseEntity<>(evaluacionAluService.guardarEvaluacionCompleta(listaEvaluacion), HttpStatus.OK);
    }

    @GetMapping("/final")
    public List<IEvaluacionPorDocente> procesarEvaluacionPorDocente(@RequestParam String periodo,
                                                                    @RequestParam String rfc){
        return evaluacionAluService.mostrarDatosTablaEvaluacionPorDocente(rfc, periodo);
    }

    @GetMapping("/final/{rfc}/tabla")
    public List<EvaluacionTablaDocenteDTO> procesarResultadosEvaluacion(@PathVariable String rfc) {
        return evaluacionAluService.procesarResultadosEvaluacionPorDocente(rfc);
    }

    @GetMapping("/final/por_materia")
    public List<IEvaluacionPorMateria> procesarEvaluacionPorMateria(@RequestParam String periodo,
                                                                              @RequestParam String materia){
        return evaluacionAluService.mostrarDatosTablaEvaluacionPorMateria(periodo, materia);
    }

    @GetMapping("/final/por_materia/tabla")
    public List<EvaluacionTablaDocenteDTO> procesarResultadosEvaluacionPorMateria(@RequestParam String materia){
        return evaluacionAluService.procesarResultadosEvaluacionPorMateria(materia);
    }

    @GetMapping("/estadistico")
    public List<EstadisticoEvaluacionDocente> estadisticoEvaluacionDocentes(@RequestParam String claveArea,
                                                                            @RequestParam String periodo){
        return evaluacionAluService.estadisticoEvaluacionDocentes(claveArea, periodo);
    }

    @GetMapping("/tipo_evaluacion/alumnos")
    public List<DocentesEvaluacionDTO> evaluacionPorAlumnos(@RequestParam String claveArea,
                                                            @RequestParam String periodo){
        return evaluacionAluService.evaluacionPorAlumnos(claveArea, periodo);
    }

    //Buscar preguntas con aspectos / Llenado manual por alumnos
    @GetMapping("/llenado_manual")
    public List<PreguntasDTO> buscarPreguntasConAspectos(){
        return evaluacionAluService.buscarPreguntasConAspectos();
    }

    @GetMapping("/valor_opciones")
    public List<ValorOpcionesEvaluacion> buscarOpciones(){
        return valorOpcionesEvaluacionService.buscarOpciones();
    }

    @PostMapping("/valor_opciones")
    public ValorOpcionesEvaluacion agregarOpcion(@RequestBody ValorOpcionesEvaluacion opcion){
        return valorOpcionesEvaluacionService.agregarOpcion(opcion);
    }

    @DeleteMapping("/valor_opciones/{opcion}")
    public void eliminarOpcion(@PathVariable Character opcion){
        valorOpcionesEvaluacionService.eliminarOpcion(opcion);
    }

    @GetMapping("/periodo_evaluacion")
    public ResponseEntity<PeriodoEscolar> mostrarPeriodoEscolar(@RequestParam String periodo){
        return new ResponseEntity<>(evaluacionAluService.mostrarPeriodoEscolar(periodo), HttpStatus.OK);
    }
    //Establecer periodo de evaluacion docente
    @PutMapping("/periodo_evaluacion")
    public ResponseEntity<PeriodoEscolar> configurarPeriodoEvaluacionDocente(@RequestBody PeriodoEvaluacionDocenteDTO periodoEvaluacion){
        return new ResponseEntity<>(evaluacionAluService.configurarPeriodoEvaluacionDocente(periodoEvaluacion), HttpStatus.OK);
    }

    //Configurar fecha de visualización de resultados de la evaluación docente
    @GetMapping("/fecha_resultados/{periodo}")
    public ResponseEntity<PeriodoEvaluacionDocenteDTO> buscarFechaResultadosEvaluacion(@PathVariable String periodo){
        return new ResponseEntity<>(evaluacionAluService.buscarFechaResultadosEvaluacion(periodo), HttpStatus.OK);
    }

    @PutMapping("/fecha_resultados")
    public ResponseEntity<PeriodoEscolar> establecerFechaResultadosEvaluacion(@RequestBody PeriodoEvaluacionDocenteDTO fecha){
        return new ResponseEntity<>(evaluacionAluService.establecerFechaResultadosEvaluacion(fecha), HttpStatus.OK);
    }
}
