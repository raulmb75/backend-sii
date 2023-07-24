package com.sii.aspirantes.aspirantes.controllerEscolares;


import com.sii.aspirantes.aspirantes.entityEscolares.GruposEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.GruposServiceEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/escolares/grupos")
public class GruposControllerEscolares {

  @Autowired
  private GruposServiceEs gruposService;


  @GetMapping(value = "/carrera/{reticula}")
  public List<GruposEs> buscarGruposPorCarrera(@PathVariable Integer reticula){
    return gruposService.buscarGruposPorCarrera(reticula);
  }

  @GetMapping(value = "/carrera/periodo/{reticula}/{periodo}")
  public List<GruposEs> buscarGruposPorCarreraYperiodo(@PathVariable Integer reticula, String periodo){
      return gruposService.buscarGruposPorCarreraYperiodo(reticula, periodo);
    }

}
