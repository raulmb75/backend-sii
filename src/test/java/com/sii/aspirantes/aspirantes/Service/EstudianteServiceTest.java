package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasAEvaluar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EstudianteServiceTest {
    @Autowired
    EstudianteService estudianteService;

    @Test
    public void obtenerMateriasParaEvaluar(){
        List<MateriasAEvaluar> materiasAEvaluar = estudianteService.obtenerMateriasParaEvaluar("20231", "90500020");
        
        materiasAEvaluar.forEach(materia ->
                System.out.println("Datos = " + materia.getNoDeControl() + "\t" +
                        materia.getMateria() + "\t" +
                        materia.getGrupo() + "\t" +
                        materia.getRfc())
        );
    }

}