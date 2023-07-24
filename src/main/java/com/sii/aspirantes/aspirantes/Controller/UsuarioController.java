package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import com.sii.aspirantes.aspirantes.Service.AccesoService;
import com.sii.aspirantes.aspirantes.error.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Representa las operaciones CRUD para los usuarios que tienen acceso al sistema (administrador del sistema o super usuario)
 */
@RestController
@RequestMapping("/api/sys_admin/usuario")
public class UsuarioController {

    private final AccesoService accesoService;

    public UsuarioController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @GetMapping
    public String mensajeBienvenidaAdmin(){
        return "Bienvenido al sistema, eres un administrador";
    }

    @GetMapping("/mostrar_usuarios")
    public List<Acceso> verUsusarios(){
        return accesoService.obtenerUsuarios();
    }
    @GetMapping("/{usuarioId}")
    public Acceso verUsuarioPorId(@PathVariable(value = "usuarioId") String usuarioId) throws UsuarioNotFoundException {
        return accesoService.obtenerUsuarioPorId(usuarioId);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Acceso> registrarUsuario(@RequestBody  Acceso usuario){
        //return accesoService.guardarUsuario(usuario);
        return new ResponseEntity<Acceso>(accesoService.guardarUsuario(usuario), HttpStatus.OK);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Acceso> actualizarUsuario(@RequestBody Acceso usuario,
                                    @PathVariable(value = "usuarioId") String usuarioId) throws UsuarioNotFoundException {

        return new ResponseEntity<Acceso>(accesoService.actualizarUsuario(usuario, usuarioId), HttpStatus.OK);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable(value = "usuarioId") String usuario){
        accesoService.eliminarUsuario(usuario);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
