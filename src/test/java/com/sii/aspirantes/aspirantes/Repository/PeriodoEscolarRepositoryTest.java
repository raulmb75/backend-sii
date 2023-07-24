package com.sii.aspirantes.aspirantes.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeriodoEscolarRepositoryTest {
    
    @Autowired
    PeriodoEscolarRepository periodoEscolarRepository;

    @Test
    public void mostrarPeriodoActual(){
        String periodo = periodoEscolarRepository.obtenerPeriodoEscolarActual();

        System.out.println("periodo = " + periodo);
    }
}