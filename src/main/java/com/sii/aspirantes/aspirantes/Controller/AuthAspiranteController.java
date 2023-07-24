package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.dto.Autenticacion;
import com.sii.aspirantes.aspirantes.Utils.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Representa la autenticación de un aspirante para acceder al sistema
 */
@RestController
@RequestMapping("/api/aspirante/acceso")
public class AuthAspiranteController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthAspiranteController(AuthenticationManager authenticationManager,
                                   JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<String> accessoAspirante(@RequestBody Autenticacion autenticacion) throws Exception {
        String token = null;
        String user = autenticacion.getTipo_usuario() + " " + autenticacion.getUsuario();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, autenticacion.getContrasena())
            );
        } catch (Exception ex) {
            throw new Exception("Contraseña o nombre de usuario incorrectos");
        }

        token = jwtUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);

        return new ResponseEntity<String>("Bienvenido al sistema",responseHeaders, HttpStatus.OK);
    }

}
