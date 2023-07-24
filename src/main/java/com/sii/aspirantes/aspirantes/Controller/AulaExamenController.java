package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Service.AulaExamenService;
import com.sii.aspirantes.aspirantes.dto.AulaExamenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/aulas_examen")
public class AulaExamenController {
    
    private final AulaExamenService aulaExamenService;

    public AulaExamenController(AulaExamenService aulaExamenService) {
        this.aulaExamenService = aulaExamenService;
    }

    @GetMapping()
    public ResponseEntity<List<AulaExamenDTO>> buscarTodas(){
        return new ResponseEntity<>(aulaExamenService.buscarAulas(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AulaExamenDTO> registrarAula(@RequestBody AulaExamenDTO aulaExamen){
        return new ResponseEntity<>(aulaExamenService.registrarAula(aulaExamen), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarAula(@RequestParam Long id){
        aulaExamenService.eliminarAula(id);
        return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
    }
}
