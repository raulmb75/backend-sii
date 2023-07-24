package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Aspirante;
import com.sii.aspirantes.aspirantes.Entity.PeriodoEscolar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

@SpringBootTest
public class AspiranteRepositoryTest {

    @Autowired
    AspiranteRepository aspiranteRepository;
    @Autowired
    PeriodoEscolarRepository periodoEscolarRepository;
    @Test
    public void guardarAspirante() {
//        Aspirante aspirante = Aspirante.builder()
//                .nombreAspirante("Lluvia")
//                .apellidoPaterno("Gonzalez")
//                .apellidoMaterno("Marquez")
        //.curp("EGML990103HMSNHG04")
        //.genero("Mujer")
        //.estadoCivil("Soltero")
        //.nacionalidad("Mexicana")
        //.carreraOp1("Contador publico")
        //.carreraOp2("Sistemas computacionales")
        //.clavePreparatoria("16TXD36PL1")
        //.anioEgreso(2021)
        //.promedioGeneral(90.5)
//                .entidadFederativaPrepa(1)
//                .entidadFederativa(1)
        //.municipio(25)
        //.folioCeneval(250)
        //.periodo(20231)
//                .tieneBeca('n')
//                .vivePadre('n')
//                .viveMadre('n')
//                .itmin('n')
//                .nip("6987")
//                .build();

        //Aspirante aspirante = Aspirante.builder().nombreAspirante("Miguel Angel").nip(4555).build();

        /*AspiranteDS aspiranteDs = AspiranteDS.builder()
                .noSolicitud(aspirante.getNoSolicitud())
                .periodo(aspirante.getPeriodo())
                .build();*/

        //aspiranteRepository.save(aspirante);
    }


    @Test
    public void actualizarAspirante() {
        /*Long id = 20230004L;
        Aspirante aspirante = Aspirante.builder()
                .nombreAspirante("Miguel")
                .apellidoPaterno("Anaya")
                .apellidoMaterno("Marquez")
                .curp("EGML990103HMSNHG04")
                .genero('H')
                .estadoCivil("Soltero")
                .nacionalidad("Mexicana")
                .carreraOp1(null)
                .carreraOp2(null)
                .clavePreparatoria(null)
                .anioEgreso(2021)
                .promedioGeneral(90.5)
                .entidadFederativaPrepa(null)
                .entidadFederativa(null)
                .municipio("AYALA")
                .folioCeneval(250)
                .periodo(20231)
                .tieneBeca('n')
                .vivePadre('n')
                .viveMadre('n')
                .itmin("n")
                .nip(6987)
                .correoElectronico("miguel_angel_AB@gmail.com")
                .telefono("7352108446")
                .build();

        Aspirante aspiranteValidado = aspiranteRepository.findById(id)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("Usuario con id: " + id + " no registrado"));


        aspiranteRepository.save(aspirante);
*/
    }

    @Test
    public void conteoDeSolicitudes(){
        Integer periodo = 20231;
        String anio = periodo.toString().substring(0,4);
        Map<Integer, String> mes = new HashMap<Integer, String>();
        mes.put(1,"Enero");
        mes.put(2,"Febrero");
        mes.put(3,"Marzo");
        mes.put(4,"Abril");
        mes.put(5,"Mayo");
        mes.put(6,"Junio");
        mes.put(7,"Julio");
        mes.put(8,"Agosto");
        mes.put(9,"Septiembre");
        mes.put(10,"Octubre");
        //Obtiene el la fecha en que se abrió el periodo de solicitudes
        PeriodoEscolar periodoNuevoIngreso = periodoEscolarRepository.findById(periodo.toString()).orElse(null);
        Date fechaInicioProceso = periodoNuevoIngreso.getInicioInscripcion();
        Date fechaFinProceso = periodoNuevoIngreso.getFinInscripcion();
        //Obtener mes en que se abrió el proceso de inscripción
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fechaInicioProceso);
        Calendar mesProximo = new GregorianCalendar();
        mesProximo.setTime(fechaInicioProceso);
        int mesInicio = ((GregorianCalendar) calendar).toZonedDateTime().getMonth().getValue();
        mesProximo.add(mesInicio, 1);
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(fechaFinProceso);

        System.out.println("Mes de apertura de proceso de inscripción: " + ((GregorianCalendar) calendar).toZonedDateTime().getMonth().getValue());
        System.out.println("Mes de cierre de proceso de inscripción: " + ((GregorianCalendar) calendar2).toZonedDateTime().getMonth().getValue());
        
        System.out.println("Periodo: " + periodo);
        System.out.println("Año: " + anio);
        String fecha1 = calendar.getTime().toString();
        System.out.println("fecha1 = " + fecha1);
        String fecha2 = mesProximo.getTime().toString();
        System.out.println("fecha2 = " + fecha2);

        Long noFichasPorMes = aspiranteRepository.contarSolcitudesPorMes(periodo, fecha1, fecha2);

        System.out.println("noFichasPorMes = " + noFichasPorMes);
    }

    @Test
    public void contarFichas(){
        Integer periodo = 20231;
        String anio = periodo.toString().substring(0,4);
        String[] enero = {anio.concat("-01-01"),anio.concat("-02-01")};
        String[] febrero = {anio.concat("-02-01"),anio.concat("-03-01")};
        String[] marzo = {anio.concat("-03-01"),anio.concat("-04-01")};
        String[] abril = {anio.concat("-04-01"),anio.concat("-05-01")};
        String[] mayo = {anio.concat("-05-01"),anio.concat("-06-01")};
        String[] junio = {anio.concat("-06-01"),anio.concat("-07-01")};
        String[] julio = {anio.concat("-07-01"),anio.concat("-08-01")};

        List<String[]> fechas = new ArrayList<>();
        fechas.add(enero);
        fechas.add(febrero);
        fechas.add(marzo);
        fechas.add(abril);
        fechas.add(mayo);
        fechas.add(junio);

        List<Long> fichasPorMes = new ArrayList<>();

        for(String[] fecha: fechas){
            String fecha1 = fecha[0];
            System.out.println("fecha1 = " + fecha1);
            String fecha2 = fecha[1];
            System.out.println("fecha2 = " + fecha2);

            fichasPorMes.add(aspiranteRepository.contarSolcitudesPorMes(periodo, fecha1, fecha2));
        }

        fichasPorMes.stream().map(ficha -> "ficha = " + ficha).forEach(System.out::println);
    }


}