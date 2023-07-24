package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Personal;
import com.sii.aspirantes.aspirantes.Service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// No implementado por ningún rol
@RestController
@RequestMapping(value = "/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public List<Personal> mostrarPersonal(){
        return personalService.buscarTodos();
    }

    @GetMapping(value = "/{rfc}")
    public Personal mostrarPersonalPorRFC(@PathVariable String rfc){
        return personalService.buscarPersonalPorRfc(rfc);
    }

    @PostMapping
    public ResponseEntity<Personal> registrarPersonal(@RequestBody Personal personal){
        return new ResponseEntity<>(personalService.guardarDatosEmpleado(personal), HttpStatus.OK);
    }

    @PutMapping(value = "/{rfc}")
    public ResponseEntity<Personal> actualizarInfoPersonal(@PathVariable String rfc, @RequestBody Personal datosEmpleado){
        datosEmpleado.setRfc(rfc);
        return new ResponseEntity<>(personalService.guardarDatosEmpleado(datosEmpleado), HttpStatus.OK);
    }

    @DeleteMapping(value = "{rfc}")
    public void eliminarPersonal(@PathVariable String rfc){
        personalService.eliminarEmpleado(rfc);
    }

    @GetMapping(value = "/area/{clave}")
    public List<Personal> mostrarPersonalPorArea(@PathVariable String clave){
        return personalService.buscarPersonalPorClaveArea(clave);
    }

    @GetMapping(value = "/lugar-nacimiento/{lugar}")
    public List<Personal> mostrarPersonalPorLugarNacimiento(@PathVariable Integer lugar){
        return personalService.buscarPersonalPorLugarNacimiento(lugar);
    }

    @GetMapping(value = "/status/{status}")
    public List<Personal> mostrarPersonalPorStatus(@PathVariable String status){
        return personalService.buscarPersonalPorStatusEmpleado(status);
    }

    @GetMapping(value = "/localidad/{localidad}")
    public List<Personal> mostrarPersonalPorLocalidad(@PathVariable String localidad){
        return personalService.buscarPersonalPorLocalidad(localidad);
    }

    @GetMapping(value = "/grado-maximo/{gradoMaxEstudios}")
    public List<Personal> mostrarPersonalPorGradoMaxEstudios(@PathVariable String gradoMaxEstudios){
        return personalService.buscarPersonalPorGradoMaxEstudios(gradoMaxEstudios);
    }

    @GetMapping(value = "/sexo/{sexo}")
    public List<Personal> mostrarPersonalPorSexo(@PathVariable Character sexo){
        return personalService.buscarPersonalPorSexo(sexo);
    }
}
