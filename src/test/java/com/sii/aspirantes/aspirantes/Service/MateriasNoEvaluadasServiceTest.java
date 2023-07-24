package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.Entity.MateriasNoEvaluadas;
import com.sii.aspirantes.aspirantes.Repository.GruposRepository;
import com.sii.aspirantes.aspirantes.Repository.MateriasNoEvaluadasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MateriasNoEvaluadasServiceTest {

    @Autowired
    private MateriasNoEvaluadasRepository materiasNoEvaluadasRepository;
    @Autowired
    private GruposRepository gruposRepository;

    @Test
    public void buscarMateriaPorGrupo(){

        Grupos grupoMateria = gruposRepository.buscarGrupoConMateria("1ak        ", 1)
                .orElseThrow(()->
                        new EntityNotFoundException("Materia/Grupo no registrado"));

        System.out.println(grupoMateria);
    }

    @Test
    public void materiaRegistrada() throws Exception {
        Grupos grupoMateria = gruposRepository.buscarGrupoConMateria("1ak", 1)
                .orElseThrow(()->
                        new EntityNotFoundException("Materia/Grupo no registrado: "));

        MateriasNoEvaluadas materia = materiasNoEvaluadasRepository.buscarPorGrupo(grupoMateria).orElse(null);

        if(Objects.nonNull(materia)){
            System.out.println("El grupo ya fue omitido");
        }

        System.out.println("El grupo NO se ha omitido");
    }

}