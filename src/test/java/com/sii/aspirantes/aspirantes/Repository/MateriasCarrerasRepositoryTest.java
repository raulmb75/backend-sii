package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MateriasCarrerasRepositoryTest {
    @Autowired
    MateriasCarrerasRepository materiasCarrerasRepository;

    @Test
    public void mostrarMateriasPorCarrera(){
        List<MateriasCarreras> listaCarreras = materiasCarrerasRepository.obtenerMateriasPorCarrera(5);

        listaCarreras.forEach(carrera -> System.out.println(carrera.getMateria()));
    }
}