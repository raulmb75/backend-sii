package com.sii.aspirantes.aspirantes.controllerEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.PersonalEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.PersonalServiceEs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escolares/personal")
public class PersonalControllerEscolares {

  private final PersonalServiceEs personalService;

  public PersonalControllerEscolares(PersonalServiceEs personalService) {
    this.personalService = personalService;
  }

  @GetMapping
  public List<PersonalEs> mostrarPersonal(){
    return personalService.buscarTodos();
  }

  @PostMapping
  public ResponseEntity<PersonalEs> registrarPersonal(@RequestBody PersonalEs personal){
    return new ResponseEntity<>(personalService.guardarDatosEmpleado(personal), HttpStatus.OK);
  }

  @PutMapping(value = "/{rfc}")
  public ResponseEntity<PersonalEs> actualizarInfoPersonal(@PathVariable String rfc, @RequestBody PersonalEs datosEmpleado){
    datosEmpleado.setRfc(rfc);
    return new ResponseEntity<>(personalService.guardarDatosEmpleado(datosEmpleado), HttpStatus.OK);
  }

  @DeleteMapping(value = "{rfc}")
  public void eliminarPersonal(@PathVariable String rfc){
    personalService.eliminarEmpleado(rfc);
  }

  @GetMapping(value = "/area/{clave}")
  public List<PersonalEs> mostrarPersonalPorArea(@PathVariable String clave){
    return personalService.buscarPersonalPorClaveArea(clave);
  }

  @GetMapping(value = "/lugar/nacimiento/{lugar}")
  public List<PersonalEs> mostrarPersonalPorLugarNacimiento(@PathVariable Integer lugar){
    return personalService.buscarPersonalPorLugarNacimiento(lugar);
  }

  @GetMapping(value = "/status/{status}")
  public List<PersonalEs> mostrarPersonalPorStatus(@PathVariable String status){
    return personalService.buscarPersonalPorStatusEmpleado(status);
  }

  @GetMapping(value = "/localidad/{localidad}")
  public List<PersonalEs> mostrarPersonalPorLocalidad(@PathVariable String localidad){
    return personalService.buscarPersonalPorLocalidad(localidad);
  }

  @GetMapping(value = "/gradoMaximo/{gradoMaxEstudios}")
  public List<PersonalEs> mostrarPersonalPorGradoMaxEstudios(@PathVariable String gradoMaxEstudios){
    return personalService.buscarPersonalPorGradoMaxEstudios(gradoMaxEstudios);
  }

  @GetMapping(value = "/sexo/{sexo}")
  public List<PersonalEs> mostrarPersonalPorSexo(@PathVariable Character sexo){
    return personalService.buscarPersonalPorSexo(sexo);
  }
}
