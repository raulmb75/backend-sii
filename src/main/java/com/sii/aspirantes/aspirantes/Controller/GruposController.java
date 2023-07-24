package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.Entity.Horarios;
import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import com.sii.aspirantes.aspirantes.Service.GruposService;
import com.sii.aspirantes.aspirantes.dto.GruposCarreraDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GruposController {

    private final GruposService gruposService;

    public GruposController(GruposService gruposService) {
        this.gruposService = gruposService;
    }

    @GetMapping
    public List<Grupos> buscarTodosGrupos(){
        return gruposService.buscarTodosGrupos();
    }

    @GetMapping("/{idGrupo}")
    public Grupos buscarGrupoPorId(@PathVariable() Integer idGrupo){
        return gruposService.buscarGrupoPorId(idGrupo);
    }

    @PostMapping
    public ResponseEntity<Grupos> registrarGrupo(@RequestBody Grupos grupo){
        return new ResponseEntity<>(gruposService.registrarGrupo(grupo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupos> actualizarDatosGrupo(@PathVariable("id") Integer idGrupo, @RequestBody Grupos grupo){
        return new ResponseEntity<>(gruposService.actualizarDatosGrupo(idGrupo, grupo), HttpStatus.OK);
    }

    public ResponseEntity<String> eliminarGrupo(Integer idGrupo){
        gruposService.eliminarGrupo(idGrupo);
        return new ResponseEntity<String>("Grupo eliminado exitosamente", HttpStatus.OK);
    }

    //Buscar por materia
    @GetMapping("/materia/{clave}")
    public List<Grupos> buscarGruposPorMateria(@PathVariable String clave){
        return gruposService.buscarGruposPorMateria(clave);
    }
    //Buscar grupos por carrera
    @GetMapping("/por_carrera")
    public List<GruposCarreraDTO> buscarGruposPorCarrera(@PathVariable String periodo,
                                                         @PathVariable Integer reticula){
        return gruposService.buscarGruposPorCarrera(reticula, periodo, null, null);
    }

    //Buscar grupos por carrera y semestre
    @GetMapping("/por_carrera_semestre")
    public List<GruposCarreraDTO> buscarGruposPorCarreraSemestre(@RequestParam String periodo,
                                                                 @RequestParam Integer reticula,
                                                                 @RequestParam Integer semestre){
        return gruposService.buscarGruposPorCarrera(reticula, periodo, null, semestre);
    }

    //Buscar por departamento
    @GetMapping("/por_departamento")
    public List<GruposCarreraDTO> buscarGruposPorDepartamento(@RequestParam String periodo,
                                                              @RequestParam String claveArea){
        return gruposService.buscarGruposPorCarrera(null, periodo, claveArea, null);
    }

    //Buscar por departamento y semestre
    @GetMapping("/por_departamento_semestre")
    public List<GruposCarreraDTO> buscarGruposPorDepartamentoSemestre(@RequestParam String periodo,
                                                                      @RequestParam String claveArea,
                                                                      @RequestParam Integer semestre){
        return gruposService.buscarGruposPorCarrera(null, periodo, claveArea, semestre);
    }

    //Buscar por periodo
    @GetMapping("/periodo/{periodo}")
    public List<Grupos> buscarGruposPorPeriodo(@PathVariable String periodo){
        return gruposService.buscarGruposPorPeriodo(periodo);
    }

    //Buscar por personal
    @GetMapping("/docente/{rfc}")
    public List<Grupos> buscarGruposPorDocente(@PathVariable String rfc){
        return gruposService.buscarGruposPorDocente(rfc);
    }

}
