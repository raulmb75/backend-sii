package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.dto.GestionCursosDTO;
import com.sii.aspirantes.aspirantes.Entity.Cursos;
import com.sii.aspirantes.aspirantes.Service.CursosService;
import com.sii.aspirantes.aspirantes.dto.ListaCursosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/cursos")
public class CursosController {

    private final CursosService cursosService;

    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    @GetMapping
    public ResponseEntity<List<GestionCursosDTO>> consultarListaCursos(){
        return new ResponseEntity<>(cursosService.consultarListaCursos(null,null), HttpStatus.OK);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<GestionCursosDTO>> consultarListaCursosPorInstructor(@RequestParam String periodo,
                                                                                    @RequestParam String rfc){
        return new ResponseEntity<>(cursosService.consultarListaCursos(periodo,rfc), HttpStatus.OK);
    }

    @GetMapping("/{clave}")
    public ResponseEntity<Cursos> consultarCurso(@PathVariable String clave){
        return new ResponseEntity<>(cursosService.consultarCurso(clave), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cursos> registrarNuevoCurso(@RequestBody Cursos cursos){
        return new ResponseEntity<>(cursosService.registrarNuevoCurso(cursos), HttpStatus.OK);
    }

    @PutMapping("/{clave}")
    public ResponseEntity<Cursos> actualizarInfoCurso(@PathVariable String clave,
                                                      @RequestBody Cursos curso){
        return new ResponseEntity<>(cursosService.actualizarInfoCurso(clave, curso), HttpStatus.OK);
    }

    @DeleteMapping("/{clave}")
    public ResponseEntity<String> eliminarCurso(@PathVariable String clave){
        cursosService.eliminarCurso(clave);
        return new ResponseEntity<>("Curso removido con éxito", HttpStatus.OK);
    }

    @GetMapping("/listas/{periodo}")
    public ResponseEntity<List<ListaCursosDTO>> listaGestionCursos(@PathVariable String periodo){
        return new ResponseEntity<>(cursosService.listaGestionCursos(periodo), HttpStatus.OK);
    }
}
