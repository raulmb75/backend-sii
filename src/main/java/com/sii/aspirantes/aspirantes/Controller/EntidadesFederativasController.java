package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.EntidadFederativa;
import com.sii.aspirantes.aspirantes.Service.EntidadFederativaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/entidades_federativas")
public class EntidadesFederativasController {

    private final EntidadFederativaService entidadFederativaService;

    public EntidadesFederativasController(EntidadFederativaService entidadFederativaService) {
        this.entidadFederativaService = entidadFederativaService;
    }

    @GetMapping
    public List<EntidadFederativa> mostrarEntidadesFederativas(){
        return entidadFederativaService.buscarEntidadesFederativas();
    }

    @GetMapping("/{id}")
    public EntidadFederativa mostrarEntidadFederativaPorClave(@PathVariable Integer id){
        return entidadFederativaService.buscarPorNoEntidad(id);
    }
}
