package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Horarios;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HorariosRepositoryTest {

    @Autowired
    HorariosRepository horariosRepository;

    @Test
    public void mostrarHorarios(){
        //Horarios ordenados por materia
        List<Horarios> horarios = horariosRepository.busquedaCompleta("20231","wg9  ","1  ", "AADN6309146Z3");
        
        horarios.forEach(horario -> System.out.println("horario = " + horario));
    }
}