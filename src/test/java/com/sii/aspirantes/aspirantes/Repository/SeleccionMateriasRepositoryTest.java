package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.SeleccionMaterias;
import com.sii.aspirantes.aspirantes.Entity.SeleccionMateriasPK;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SeleccionMateriasRepositoryTest {
    @Autowired
    SeleccionMateriasRepository seleccionMateriasRepository;


    @Test
    public void mostrarPorNoDeControl(){
        List<SeleccionMaterias> seleccionMaterias = seleccionMateriasRepository.buscarSeleccionMateriasPorNoControl("17680105");

        for(SeleccionMaterias datos: seleccionMaterias){
            System.out.println("Materia = " + datos.getSeleccionMateriasPK().getMateria());
        }
    }

    @Test
    public void mostrarPormateria(){
        List<SeleccionMaterias> seleccionMaterias = seleccionMateriasRepository.buscarSeleccionMateriasPorMateria("LI-101");

        for(SeleccionMaterias datos: seleccionMaterias){
            System.out.println("Alumnos = " + datos.getSeleccionMateriasPK().getNoDeControl());
        }
    }

    @Test
    public void mostrarPorCalificacion(){
        List<SeleccionMaterias> seleccionMaterias = seleccionMateriasRepository
                .buscarSeleccionMateriasPorCalificacion(0D, 90D);

        for(SeleccionMaterias datos: seleccionMaterias){
            System.out.println("Alumnos = " + datos.getSeleccionMateriasPK().getNoDeControl());
        }
    }

}