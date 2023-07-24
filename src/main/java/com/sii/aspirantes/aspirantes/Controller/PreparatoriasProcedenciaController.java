package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.PreparatoriasProcedencia;
import com.sii.aspirantes.aspirantes.Service.PreparatoriasProcedenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preparatorias_procedencia")
public class PreparatoriasProcedenciaController {

    private final PreparatoriasProcedenciaService preparatoriasProcedenciaService;

    public PreparatoriasProcedenciaController(PreparatoriasProcedenciaService preparatoriasProcedenciaService) {
        this.preparatoriasProcedenciaService = preparatoriasProcedenciaService;
    }

    @GetMapping
    public List<PreparatoriasProcedencia> mostrarPreparatoriasDeProcedencia(){
        return preparatoriasProcedenciaService.mostrarPreparatoriasDeProcedencia();
    }

    @GetMapping("/{clave}")
    public PreparatoriasProcedencia mostrarPreparatoriaDeProcedenciaPorClave(@PathVariable String clave){
        return preparatoriasProcedenciaService.buscarPreparatoriaPorClave(clave);
    }

    //Buscar por entidad federativa
    @GetMapping("/estado/{estado}")
    public List<PreparatoriasProcedencia> mostrarPreparatoriasPorEntidadFederativa(@PathVariable Integer estado){
        return preparatoriasProcedenciaService.buscarPreparatoriasPorEntidadFederativa(estado);
    }
    //Buscar por municipio
    @GetMapping("/municipio/{municipio}")
    public List<PreparatoriasProcedencia> mostrarPreparatoriasPorMunicipio(@PathVariable String municipio){
        return preparatoriasProcedenciaService.buscarPreparatoriasPorMunicipio(municipio);
    }
    //Buscar por servicio
    @GetMapping("/servicio/{servicio}")
    public List<PreparatoriasProcedencia> mostrarPreparatoriasPorServicio(@PathVariable String servicio){
        return preparatoriasProcedenciaService.buscarPreparatoriasPorServicio(servicio);
    }
    //Buscar por sostenimiento
    @GetMapping("/sostenimiento/{sostenimiento}")
    public List<PreparatoriasProcedencia> mostrarPreparatoriasPorSostenimiento(@PathVariable String sostenimiento){
        return preparatoriasProcedenciaService.buscarPreparatoriasPorSostenimiento(sostenimiento);
    }
}
