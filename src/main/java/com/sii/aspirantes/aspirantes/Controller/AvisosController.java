package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Avisos;
import com.sii.aspirantes.aspirantes.Service.AvisosService;
import com.sii.aspirantes.aspirantes.dto.AvisoRequest;
import com.sii.aspirantes.aspirantes.dto.AvisoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/avisos")
public class AvisosController {
    private final AvisosService avisosService;

    public AvisosController(AvisosService avisosService) {
        this.avisosService = avisosService;
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<List<AvisoResponse>> mostrarListaAvisosPorUsuario(@PathVariable String usuario){
        return new ResponseEntity<>(avisosService.mostrarListaAvisosPorUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AvisoResponse> mostrarAvisoPorId(@RequestParam Long id,
                                           @RequestParam String usuario){
        return new ResponseEntity<>(avisosService.mostrarAvisoPorId(usuario, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AvisoResponse> crearNuevoAviso(@RequestBody AvisoRequest avisoRequest){
        return new ResponseEntity<>(avisosService.crearNuevoAviso(avisoRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvisoResponse> modificarInformacionAviso(@PathVariable Long id,
                                            @RequestBody AvisoRequest avisoRequest){
        return new ResponseEntity<>(avisosService.modificarInformacionAviso(id, avisoRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerAviso(@PathVariable Long id){
        avisosService.removerAviso(id);
        return new ResponseEntity<>("Aviso removido con éxito", HttpStatus.OK);
    }
}
