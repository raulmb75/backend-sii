package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import com.sii.aspirantes.aspirantes.Entity.Aspirante;
import com.sii.aspirantes.aspirantes.Entity.Roles;
import com.sii.aspirantes.aspirantes.Repository.AccesoRepository;
import com.sii.aspirantes.aspirantes.Repository.AspiranteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccesoRepository accesoRepository;
    @Autowired
    private AspiranteRepository aspiranteRepository;

    public CustomUserDetailsService() {

    }

    private List<SimpleGrantedAuthority> roles;
    private String userAux;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("PERSONAL ")) {
            userAux = username.replace("PERSONAL ", "");
            return buildUsuario(userAux);
        } else if (username.startsWith("ASPIRANTE ")) {
            userAux = username.replace("ASPIRANTE ", "");
            return buildAspirante(userAux);

        }
        //return null;
        throw new UsernameNotFoundException("No se encontró el usuario " + username);

    }

    public UserDetails buildUsuario(String username) {
        Acceso user = accesoRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con nombre " + username + " no existe"));

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getIdrol().getRol().name()));
        return new User("PERSONAL " + user.getUsuario(), user.getContrasena(), authorities);
    }

    public UserDetails buildAspirante(String username) {
        Aspirante aspirante = aspiranteRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("El aspirante con id " + username + " no existe"));
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(aspirante.getRol().getRol().name()));
        System.out.println("authorities = " + aspirante.getRol().getRol().name());
        return new User("ASPIRANTE " + aspirante.getNoSolicitud(),
                new BCryptPasswordEncoder().encode(String.valueOf(aspirante.getNip())), authorities);
    }


    //No está implementado
    public static void metodoAlternativo() {
        String user = "     ASPIRANTE 20230004   ";
        String username = user.trim(); //Quita los espacios del inicio y del final
        String userRole = username.replace(username.substring(username.indexOf(" ")), "");

        switch (userRole) {
            case "ADMINISTRADOR": {
                System.out.println("Eres un administrador");
                break;
            }
            case "ASPIRANTE": {
                System.out.println("Eres un aspirante");
                break;
            }
            default:
                System.out.println("Rol " + userRole + " no reconocido");
        }
    }
}
