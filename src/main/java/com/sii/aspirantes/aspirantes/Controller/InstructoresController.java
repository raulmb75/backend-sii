package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Instructor;
import com.sii.aspirantes.aspirantes.Service.InstructoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/instructores")
public class InstructoresController {

    private final InstructoresService instructoresService;

    public InstructoresController(InstructoresService instructoresService) {
        this.instructoresService = instructoresService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> consultarListaInstructores(){
        return new ResponseEntity<>(instructoresService.consultarListaInstructores(), HttpStatus.OK);
    }

    @GetMapping("/{rfc}")
    public ResponseEntity<Instructor> consultarInstructorPorId(@PathVariable String rfc){
        return new ResponseEntity<>(instructoresService.consultarInstructorPorId(rfc), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Instructor> registrarNuevoInstructor(@RequestBody Instructor instructor){
        return new ResponseEntity<>(instructoresService.registrarNuevoInstructor(instructor), HttpStatus.OK);
    }

    @PutMapping("/{rfc}")
    public ResponseEntity<Instructor> actualizarInfoInstructor(@PathVariable String rfc,
                                                               @RequestBody Instructor instructor){

        return new ResponseEntity<>(instructoresService.actualizarInfoInstructor(rfc, instructor), HttpStatus.OK);
    }

    @DeleteMapping("/{rfc}")
    public ResponseEntity<String> eliminarInstructor(@PathVariable String rfc){
        instructoresService.eliminarInstructor(rfc);
        return new ResponseEntity<>("Instructor removido con éxito", HttpStatus.OK);
    }
}
