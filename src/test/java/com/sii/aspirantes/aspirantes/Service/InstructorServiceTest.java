package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InstructorServiceTest {

    @Autowired
    InstructoresService instructoresService;
    
    @Test
    void registrarNuevoInstructor() {
        Instructor instructor = new Instructor();

        instructor.setRfc("AACA910830VD3");
        instructor.setPassword("juan15");
        instructor.setNombre("Juan José");
        instructor.setApellidoPaterno("Guevara");
        instructor.setApellidoMaterno("Vázquez");
        instructor.setCorreoElectronico("juan@gmail.com");
        instructor.setGenero('M');
        instructor.setTelefono("7352108446");
        instructor.setDomicilio("Calle Otililio Montaño no. 5, Col. Emiliano Zapata 62715, Ayala, Morelos");

        instructoresService.registrarNuevoInstructor(instructor);
    }
}