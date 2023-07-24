package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Notificaciones;
import com.sii.aspirantes.aspirantes.Service.NotificacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Notificaciones por parte del rol DDA
@RestController
@RequestMapping("/api/dda/notificaciones")
public class NotificacionesController {
    private final NotificacionesService notificacionesService;

    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }

    @GetMapping
    public ResponseEntity<List<Notificaciones>> obtenerNotificaciones(){
        return new ResponseEntity<>(notificacionesService.obtenerNotificaciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerNotificacionPorId(@PathVariable Long id){
        return new ResponseEntity<>(notificacionesService.obtenerNotificacionPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notificaciones> guardarNotificacion(@RequestBody Notificaciones notificacion){
        return new ResponseEntity<>(notificacionesService.guardarNotificacion(notificacion), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificaciones> actualizarNotificacion(@RequestBody Notificaciones notificacion,
                                                              @PathVariable Long id){
        return new ResponseEntity<>(notificacionesService.actualizarNotificacion(id, notificacion), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarNotificacion(Long id){
        notificacionesService.eliminarNotificacion(id);
        return new ResponseEntity<>("Notificacion eliminada", HttpStatus.OK);
    }
}
