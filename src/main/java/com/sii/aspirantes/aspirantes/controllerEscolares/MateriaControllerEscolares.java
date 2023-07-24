package com.sii.aspirantes.aspirantes.controllerEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.MateriaEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.MateriaServiceEs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/escolares/materias")
public class MateriaControllerEscolares {
  private final MateriaServiceEs materiaService;

  public MateriaControllerEscolares(MateriaServiceEs materiaService) {
    this.materiaService = materiaService;
  }

  @GetMapping
  public List<MateriaEs> mostrarTodasMaterias(){
    return materiaService.buscarTodasMaterias();
  }

  @GetMapping(value = "/{claveMateria}")
  public MateriaEs buscarMateriaPorClave(@PathVariable String claveMateria){
    return materiaService.buscarMateriaPorClave(claveMateria);
  }

  @PostMapping
  public ResponseEntity<MateriaEs> registrarMateria(@RequestBody MateriaEs materia){
    return new ResponseEntity<>(materiaService.registrarMateria(materia), HttpStatus.OK);
  }

  @PutMapping(value = "/{claveMateria}")
  public ResponseEntity<MateriaEs> actualizarDatosMateria(@PathVariable String claveMateria,
                                                          @RequestBody MateriaEs materia){
    return new ResponseEntity<>(materiaService.actualizarDatosMateria(claveMateria, materia), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{claveMateria}")
  public void eliminarMateria(@PathVariable String claveMateria){
    materiaService.eliminarMateria(claveMateria);
  }

  @GetMapping(value = "/nivelEscolar/{nivelEscolar}")
  public List<MateriaEs> mostrarMateriaPorNivelEscolar(@PathVariable Character nivelEscolar){
    return materiaService.buscarMateriaPorNivelEscolar(nivelEscolar);
  }

  @GetMapping(value = "/area/{claveArea}")
  public List<MateriaEs> mostrarMateriaPorDepartamento(@PathVariable String claveArea){
    return materiaService.buscarMateriaPorDepartamento(claveArea);
  }

  @GetMapping(value = "/tipo/{tipoMateria}")
  public List<MateriaEs> mostrarMateriaPorTipo(@PathVariable Integer tipoMateria){
    return materiaService.buscarMateriaPorTipo(tipoMateria);
  }
}
