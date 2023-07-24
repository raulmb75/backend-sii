package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Carrera;
import com.sii.aspirantes.aspirantes.Service.CarreraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//No implementado por ningún rol (DDA - ASP)
@RestController
@RequestMapping(value = "/api/carrera")
public class CarreraController {

    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public List<Carrera> mostrarCarreras(){
        return carreraService.buscarTodasCarreras();
    }

    @GetMapping(value = "/{reticula}")
    public Carrera mostrarCarreraPorReticula(@PathVariable Integer reticula){
        return carreraService.buscarCarreraPorReticula(reticula);
    }

    @GetMapping(value = "/nombre/{nombre}")
    public List<Carrera> mostrarCarreraPorNombre(@PathVariable String nombre){
        return carreraService.buscarCarreraPorNombre(nombre);
    }

    @GetMapping(value = "/nivel-escolar/{nivel}")
    public List<Carrera> mostrarCarrerasPorNivelEscolar(@PathVariable Character nivel){
        return carreraService.buscarCarreraPorNivelEscolar(nivel);
    }

    @GetMapping(value = "/creditos/{creditos}")
    public List<Carrera> mostrarCarrerasPorCreditos(@PathVariable Integer creditos){
        return carreraService.buscarCarreraPorCreditosTotales(creditos);
    }
}
