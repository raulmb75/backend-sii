package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Service.AccesoService;
import com.sii.aspirantes.aspirantes.dto.Autenticacion;
import com.sii.aspirantes.aspirantes.Utils.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Representa el acceso al sistema (para el personal)
 */
@RestController
@RequestMapping("/api/acceso")
public class AccesoController {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AccesoService accesoService;

    public AccesoController(JWTUtil jwtUtil,
                            AuthenticationManager authenticationManager, AccesoService accesoService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.accesoService = accesoService;
    }

    @PostMapping
    public ResponseEntity<String> autenticacionPersonal(@RequestBody Autenticacion autenticacion) throws Exception {
        String token = null;
        String user = autenticacion.getTipo_usuario() + " " + autenticacion.getUsuario();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, autenticacion.getContrasena())
            );
        } catch (Exception ex) {
            throw new Exception("Usuario o contraseña incorrectos");
        }

        token = jwtUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);
        accesoService.registrarUltimoAcceso(autenticacion.getUsuario());
        return new ResponseEntity<String>("Bienvenido al sistema",responseHeaders, HttpStatus.OK);
    }


    //Cambio de contraseña
    @PostMapping("/cambiar_contrasena/{nuevaContrasena}")
    public ResponseEntity<String> cambiarContrasenaUsuarios(@RequestBody Autenticacion usuario,
                                                            @PathVariable String nuevaContrasena) throws Exception {
        accesoService.cambiarContrasenia(usuario, nuevaContrasena);
        return new ResponseEntity<>("Contraseña cambiada exitósamente.", HttpStatus.OK);
    }
}
