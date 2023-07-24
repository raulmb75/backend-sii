package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.CarrerasEvaluan;
import com.sii.aspirantes.aspirantes.Service.CarreraService;
import com.sii.aspirantes.aspirantes.Service.CarrerasEvaluanService;
import com.sii.aspirantes.aspirantes.dto.evaluacion.CarrerasEvaluanDTO;
import com.sii.aspirantes.aspirantes.dto.evaluacion.ListaCarrerasEvaluanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/evaluacion/carrerasEvaluan")
public class CarrerasEvaluanController {
    
    private final CarrerasEvaluanService carrerasEvaluanService;
    private final CarreraService carreraService;

    public CarrerasEvaluanController(CarrerasEvaluanService carrerasEvaluanService,
                                     CarreraService carreraService) {
        this.carrerasEvaluanService = carrerasEvaluanService;
        this.carreraService = carreraService;
    }

    @GetMapping
    public ResponseEntity<List<CarrerasEvaluanDTO>> mostrarCarrerasEvaluan(){
        return new ResponseEntity<>(carrerasEvaluanService.mostrarCarrerasEvaluan(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarrerasEvaluan> agregarCarreraEvalua(@RequestParam Integer reticula){
        return new ResponseEntity<>(carrerasEvaluanService.agregarCarreraEvalua(reticula), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCarreraEvalua(@PathVariable Long id){
        carrerasEvaluanService.eliminarCarreraEvalua(id);
        return new ResponseEntity<>("Carrera removida con éxito", HttpStatus.OK);
    }

    @GetMapping("/lista_carreras")
    public ResponseEntity<List<ListaCarrerasEvaluanDTO>> buscarCarreras(){
        return new ResponseEntity<>(carreraService.buscarCarreras(), HttpStatus.OK);
    }
}
