package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Instructor;
import com.sii.aspirantes.aspirantes.Repository.InstructoresRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstructoresService {

    private final InstructoresRepository instructoresRepository;

    public InstructoresService(InstructoresRepository instructoresRepository) {
        this.instructoresRepository = instructoresRepository;
    }

    public List<Instructor> consultarListaInstructores(){
        return instructoresRepository.findAll();
    }

    public Instructor consultarInstructorPorId(String rfc){
        Instructor instructor = instructoresRepository.findById(rfc)
                .orElseThrow(()->
                        new UsernameNotFoundException("Instructor con RFC " + rfc + " no encontrado." ));

        return instructor;
    }

    public Instructor registrarNuevoInstructor(Instructor instructor){
        instructor.setFechaRegistro(new Date());
        return instructoresRepository.save(instructor);
    }

    public Instructor actualizarInfoInstructor(String rfc, Instructor instructor){
        Instructor instructorRegistrado = instructoresRepository.findById(rfc)
                .orElseThrow(()->
                        new UsernameNotFoundException("Instructor con RFC " + rfc + " no encontrado." ));

            instructorRegistrado.setRfc(instructor.getRfc());
            instructorRegistrado.setPassword(instructor.getPassword());
            instructorRegistrado.setPassword(instructor.getPassword());
            instructorRegistrado.setNombre(instructor.getPassword());
            instructorRegistrado.setApellidoPaterno(instructor.getApellidoPaterno());
            instructorRegistrado.setApellidoMaterno(instructor.getApellidoMaterno());
            instructorRegistrado.setCorreoElectronico(instructor.getCorreoElectronico());
            instructorRegistrado.setGenero(instructor.getGenero());
            instructorRegistrado.setTelefono(instructor.getTelefono());
            instructorRegistrado.setDomicilio(instructor.getDomicilio());

        return instructoresRepository.save(instructorRegistrado);
    }

    public void eliminarInstructor(String rfc){
        instructoresRepository.deleteById(rfc);
    }
}
