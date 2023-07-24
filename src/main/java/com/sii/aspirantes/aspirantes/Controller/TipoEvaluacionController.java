package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.TiposEvaluacion;
import com.sii.aspirantes.aspirantes.Service.TiposEvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/evaluacion/tipos")
public class TipoEvaluacionController {
    
    private final TiposEvaluacionService tiposEvaluacionService;

    public TipoEvaluacionController(TiposEvaluacionService tiposEvaluacionService) {
        this.tiposEvaluacionService = tiposEvaluacionService;
    }

    @GetMapping
    public ResponseEntity<List<TiposEvaluacion>> mostrarTiposEvaluacion(){
        return new ResponseEntity<>(tiposEvaluacionService.mostrarTiposEvaluacion(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TiposEvaluacion> agregarTipoEvaluacion(@RequestBody TiposEvaluacion evaluacion){
        return new ResponseEntity<>(tiposEvaluacionService.agregarTipoEvaluacion(evaluacion), HttpStatus.OK);
    }

    @PutMapping("/{encuesta}")
    public ResponseEntity<TiposEvaluacion> modificarTiposEvaluacion(@PathVariable Character encuesta,
                                                    @RequestBody TiposEvaluacion tiposEvaluacion){
        return new ResponseEntity<>(tiposEvaluacionService.modificarTiposEvaluacion(encuesta, tiposEvaluacion), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipoEvaluacion(@PathVariable Character id){
        tiposEvaluacionService.eliminarTipoEvaluacion(id);
        return new ResponseEntity<>("Elemento eliminado", HttpStatus.OK);
    }
}
