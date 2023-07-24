package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstructoresServiceTest {

    @Autowired
    private InstructoresService instructoresService;

    @Test
    public void registrarInstrctor(){
        List<Instructor> listaInstructores = new ArrayList<>();

        Instructor instructor = Instructor.builder()
                .rfc("AABDFFV2065")
                .nombre("Miguel")
                .apellidoPaterno("Anaya")
                .apellidoMaterno("Bahena")
                .correoElectronico("miguel14@gmail.com")
                .password("12345")
                .fechaRegistro(new Date())
                .build();

        Instructor instructor2 = Instructor.builder()
                .rfc("AABDFFV2016")
                .nombre("Juan")
                .apellidoPaterno("García")
                .apellidoMaterno("Guerrero")
                .correoElectronico("juan15@gmail.com")
                .password("12345")
                .fechaRegistro(new Date())
                .build();

        Instructor instructor3 = Instructor.builder()
                .rfc("AABDFFV2014")
                .nombre("Pedro")
                .apellidoPaterno("Riquelme")
                .apellidoMaterno("Pérez")
                .correoElectronico("pedro@gmail.com")
                .password("12345")
                .fechaRegistro(new Date())
                .build();

        listaInstructores.add(instructor);
        listaInstructores.add(instructor2);
        listaInstructores.add(instructor3);

        instructoresService.registrarNuevoInstructor(instructor);
        instructoresService.registrarNuevoInstructor(instructor2);
        instructoresService.registrarNuevoInstructor(instructor3);

    }
}