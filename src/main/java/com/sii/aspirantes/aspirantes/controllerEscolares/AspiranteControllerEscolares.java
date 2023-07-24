package com.sii.aspirantes.aspirantes.controllerEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.FichaAspiranteDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.AspiranteEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.AspiranteServiceEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/escolares/aspirantes")
public class AspiranteControllerEscolares {

  @Autowired
  private AspiranteServiceEs aspiranteService;

  @GetMapping("/obtener/todos/aspirantes")
  public List<AspiranteEs> getAllAspirantes() {
    return aspiranteService.getAllAspirantes();
  }

  @GetMapping(value = "/obtener/aspirantes/{curp}")
  public AspiranteEs obtenerAspirantePorCurp(@PathVariable String curp){
    return aspiranteService.obtenerAspirantePorCurp(curp);
  }

  @GetMapping("/generar/ficha/{curp}")
  public List<FichaAspiranteDTO> buscarDatosFichaAspirante(@PathVariable String curp) {
    //System.out.println(noDeControl);
    return aspiranteService.buscarDatosFichaAspirante(curp);
  }
}
