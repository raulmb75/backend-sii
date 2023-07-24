package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Preguntas;
import com.sii.aspirantes.aspirantes.Entity.PreguntasPK;
import com.sii.aspirantes.aspirantes.Service.PreguntasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasController {

    private final PreguntasService preguntasService;

    public PreguntasController(PreguntasService preguntasService) {
        this.preguntasService = preguntasService;
    }

    @GetMapping
    public List<Preguntas> mostrarPreguntas(@RequestParam String periodo){
        return preguntasService.buscarPreguntas(periodo);
    }

    @GetMapping("/{id}")
    public Preguntas mostrarPreguntaPorId(@PathVariable PreguntasPK id){
        return preguntasService.buscarPreguntaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Preguntas> agregarPregunta(@RequestBody Preguntas pregunta){
        return new ResponseEntity<Preguntas>(preguntasService.guardarPregunta(pregunta), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preguntas> actualizarPregunta(@RequestBody Preguntas pregunta, @PathVariable PreguntasPK id){
        pregunta.setPreguntasPK(id);
        return new ResponseEntity<Preguntas>(preguntasService.guardarPregunta(pregunta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminarPregunta(@PathVariable PreguntasPK id){
        preguntasService.eliminarPregunta(id);
    }

    //Buscar por aspecto
    @GetMapping("/aspecto/{aspecto}")
    public List<Preguntas> buscarPreguntasPorAspecto(@PathVariable Character aspecto){
        return preguntasService.buscarPreguntasPorAspecto(aspecto);
    }

    //Buscar por consecutivo
    @GetMapping("/consecutivo/{consecutivo}")
    public List<Preguntas> buscarPreguntasPorConsecutivo(@PathVariable Integer consecutivo){
        return preguntasService.buscarPreguntasPorConsecutivo(consecutivo);
    }

    //Buscar por encuesta
    @GetMapping("/encuesta/{encuesta}")
    public List<Preguntas> buscarPreguntasPorEncuesta(@PathVariable Character encuesta){
        return preguntasService.buscarPreguntasPorEncuesta(encuesta);
    }

    //Buscar por tipo de encuesta y aspecto
    @GetMapping("/encuesta/{encuesta}/aspecto/{aspecto}")
    public List<Preguntas> buscarPreguntasPorEncuestaAspecto(@PathVariable Character encuesta,
                                                             @PathVariable Character aspecto){
        return preguntasService.buscarPreguntasPorEncuestaAspecto(encuesta, aspecto);
    }

}
