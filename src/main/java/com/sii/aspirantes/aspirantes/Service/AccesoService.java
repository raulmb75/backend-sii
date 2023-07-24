package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import com.sii.aspirantes.aspirantes.Repository.AccesoRepository;
import com.sii.aspirantes.aspirantes.dto.Autenticacion;
import com.sii.aspirantes.aspirantes.error.UsuarioNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AccesoService{

    private final AccesoRepository accesoRepository;

    public AccesoService(AccesoRepository accesoRepository) {
        this.accesoRepository = accesoRepository;
    }

    public Acceso guardarUsuario(Acceso acceso) {
        acceso.setUltimoAcceso(LocalDateTime.now());
        acceso.setContrasena(new BCryptPasswordEncoder().encode(acceso.getContrasena()));
        return accesoRepository.save(acceso);
    }

    public List<Acceso> obtenerUsuarios() {
        return accesoRepository.findAll();
    }

    public Acceso obtenerUsuarioPorId(String usuario) throws UsuarioNotFoundException {
        Acceso usuarioRegistrado = accesoRepository.findById(usuario)
                .orElseThrow(() ->  new UsuarioNotFoundException("Usuario " + usuario + " no encontrado")
                );

        return usuarioRegistrado;
    }

    public Acceso actualizarUsuario(Acceso usuario, String usuarioId) throws UsuarioNotFoundException {
        accesoRepository.findById(usuarioId)
                .orElseThrow(() ->  new UsuarioNotFoundException("Usuario " + usuario + " no encontrado")
                );

        usuario.setContrasena(new BCryptPasswordEncoder().encode(usuario.getContrasena()));
        return accesoRepository.save(usuario);
    }

    public void eliminarUsuario(String usuario) {
        accesoRepository.deleteById(usuario);
    }

    public void registrarUltimoAcceso(String usuario) {
        Acceso usuarioExistente = accesoRepository.findById(usuario).get();

        usuarioExistente.setUltimoAcceso(LocalDateTime.now());
        accesoRepository.save(usuarioExistente);
    }

    public void cambiarContrasenia(Autenticacion usuarioAnterior, String nuevaContrasena) throws Exception {
        Acceso usuario = accesoRepository.findById(usuarioAnterior.getUsuario())
                .orElseThrow(()->
                        new UsernameNotFoundException("Usuario no encontrado"));

        // Codifica la nueva contraseña
        String encodedPassword = new BCryptPasswordEncoder().encode(nuevaContrasena);

        //Compara la contraseña ingresada con la guardada en la BD (antes de cambiarla)
        if(!new BCryptPasswordEncoder().matches(usuarioAnterior.getContrasena(), usuario.getContrasena())){
            throw new Exception("No fue posible cambiar la contraseña");
        }

        // Actualiza la contraseña del usuario
        usuario.setContrasena(encodedPassword);
        accesoRepository.save(usuario);
    }
}
