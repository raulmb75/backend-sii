package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.dto.aspirante.ComparativoSolicitudesDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@SpringBootTest
class AspiranteServiceTest {

    @Autowired
    AspiranteService aspiranteService;

    @Test
    void validarAspirantePorCurp() {

        System.out.println("¿Aspirante registrado?: " + aspiranteService.validarCurpRegistrado("AABM990301HMSNHG26"));
    }

    @Test
    public void mostrarComparativoDeFichas() throws ParseException {
        //considerar el periodo en la consulta (repositorio)

        List<ComparativoSolicitudesDTO> listaFichas = aspiranteService.comparativoSolicitudesAspirante(20231);

        listaFichas.stream().map(fichas -> fichas.getNumero() + "\t" + fichas.getSubtotal() + "\t" + fichas.getRangoFechas()).forEach(System.out::println);
    }

    @Test
    public void mostrarFechaActual(){
        TimeZone timeZone = TimeZone.getTimeZone("GMT-6");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        formato.setTimeZone(timeZone);
        Calendar fechaLimite = Calendar.getInstance(timeZone); //Representa la fecha actual
        //fechaLimite.setTimeZone(timeZone);

        System.out.println("Fecha actual: " + fechaLimite);


    }


}