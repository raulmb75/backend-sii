package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Estudiante;
import com.sii.aspirantes.aspirantes.Service.EstudianteService;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasAEvaluar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//No implementado por ningún rol (DDA - ASP)
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> buscarTodosEstudiantes(){
        return estudianteService.buscarTodosEstudiantes();
    }

    @GetMapping("/{noControl}")
    public Estudiante buscarEstudiantePorNoDeControl(@PathVariable String noControl){
        return estudianteService.buscarEstudiantePorNoDeControl(noControl);
    }

    @PostMapping("/{noControl}")
    public ResponseEntity<Estudiante> guardarDatosEstudiante(@RequestBody Estudiante estudiante){
        return new ResponseEntity<>(estudianteService.guardarDatosEstudiante(estudiante), HttpStatus.OK);
    }

    @PutMapping("/{noControl}")
    public ResponseEntity<Estudiante> actualizarDatosEstudiante(@PathVariable String noControl,
                                                                @RequestBody Estudiante estudiante){
        //estudiante.setNoDeControl(noControl);
        //hacer validadcion por número de controntrol
        return new ResponseEntity<>(estudianteService.guardarDatosEstudiante(estudiante), HttpStatus.OK);
    }

    @DeleteMapping("/{noControl}")
    public void eliminarEstudiante(@PathVariable String noControl){
        estudianteService.eliminarEstudiante(noControl);
    }

    @GetMapping("/carrera/{reticula}")
    public List<Estudiante> mostrarEstudiantesPorCarrera(@PathVariable Integer reticula){
        return estudianteService.buscarEstudiantesPorCarrera(reticula);
    }

    @GetMapping("/evaluacion/mostrar_materias")
    public List<MateriasAEvaluar> obtenerMateriasParaEvaluar(@RequestParam String periodo,
                                                             @RequestParam String noDeControl){
        return estudianteService.obtenerMateriasParaEvaluar(periodo, noDeControl);
    }
}
