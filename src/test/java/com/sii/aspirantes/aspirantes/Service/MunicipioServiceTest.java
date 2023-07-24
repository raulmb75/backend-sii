package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Municipio;
import com.sii.aspirantes.aspirantes.Entity.MunicipioPK;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MunicipioServiceTest {

    @Autowired
    private MunicipioService municipioService;
    @Test
    void buscarPorClave() {

        Municipio municipio = municipioService.buscarPorClave(new MunicipioPK(17,5));

        System.out.println("El municipio es: " + municipio.getNombreMunicipio());
    }

    @Test
    void buscarMunicipiosPorEstado() {
        List<Municipio> municipiosList = municipioService.buscarMunicipiosPorEstado(17);

        municipiosList.forEach(municipio ->
                System.out.println("Municipio = " + municipio.getNombreMunicipio()));
    }
}