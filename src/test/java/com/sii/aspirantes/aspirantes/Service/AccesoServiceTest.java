package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Utils.RegexValidators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccesoServiceTest {

    @Autowired
    AccesoService accesoService;

    /*@Test
    public void cambiarContrasena() throws Exception {
        Autenticacion usuarioAnterior= new Autenticacion("administrador", "miguel_anaya", "12345");
        String nuevaContrasena = "miguel";
        accesoService.cambiarContrasenia(usuarioAnterior, nuevaContrasena);
    }*/

    @Test
    public void validarEmail(){
        String validEmail = "miguel1212@gmail.com";
        String invalidEmail = "miguel_gmail.com";

        System.out.println("Email válido: " + RegexValidators.validateEmail(validEmail));
        System.out.println("Email válido: " + RegexValidators.validateEmail(invalidEmail));
    }

    @Test
    public void validarCurp(){
        String validCurp = "AABM990301HMSNHG04";
        String curp2 = "AAAA000101HDFXYZ01";
        String invalidCurp = "CURPNOVALIDO";

        System.out.println("CURP válido: " + RegexValidators.validateCURP(validCurp));
        System.out.println("CURP válido: " + RegexValidators.validateCURP(invalidCurp));
        System.out.println("CURP válido: " + RegexValidators.validateCURP(curp2));
    }


}