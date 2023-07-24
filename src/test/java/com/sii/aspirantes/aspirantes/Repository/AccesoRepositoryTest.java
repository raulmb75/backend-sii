package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AccesoRepositoryTest {

    @Autowired
    AccesoRepository accesoRepository;

    @Test
    public void crearUsuario(){
        List<Acceso> usuarios = new ArrayList<>();

        /*Acceso usuario1 = new Acceso("miguel_anaya", "Miguel Angel",
                new BCryptPasswordEncoder().encode("miguel"), 'N', 'A', LocalDateTime.now());

        Acceso usuario2 = new Acceso("miguel_bahena", "Miguel Bahena",
                new BCryptPasswordEncoder().encode("miguel"), 'N', 'A', LocalDateTime.now());
*/
//        usuarios.add(usuario1);
//        usuarios.add(usuario2);

        accesoRepository.saveAll(usuarios);
    }


}