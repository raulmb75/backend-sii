package com.sii.aspirantes.aspirantes.controllerEscolares;


import com.sii.aspirantes.aspirantes.entityEscolares.CarreraEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.CarreraServiceEs;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "escolares/carrera")
public class CarreraControllerEscolares {

  private final CarreraServiceEs carreraService;

  public CarreraControllerEscolares(CarreraServiceEs carreraService) {
    this.carreraService = carreraService;
  }

  @GetMapping
  public List<CarreraEs> mostrarCarreras(){
    return carreraService.buscarTodasCarreras();
  }

  @GetMapping(value = "/{reticula}")
  public CarreraEs mostrarCarreraPorReticula(@PathVariable Integer reticula){
    return carreraService.buscarCarreraPorReticula(reticula);
  }

  @GetMapping(value = "/nombre/{nombre}")
  public List<CarreraEs> mostrarCarreraPorNombre(@PathVariable String nombre){
    return carreraService.buscarCarreraPorNombre(nombre);
  }

  @GetMapping(value = "/nivelEscolar/{nivel}")
  public List<CarreraEs> mostrarCarrerasPorNivelEscolar(@PathVariable Character nivel){
    return carreraService.buscarCarreraPorNivelEscolar(nivel);
  }

  @GetMapping(value = "/creditos/{creditos}")
  public List<CarreraEs> mostrarCarrerasPorCreditos(@PathVariable Integer creditos){
    return carreraService.buscarCarreraPorCreditosTotales(creditos);
  }

}
