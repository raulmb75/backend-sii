package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Notificaciones;
import com.sii.aspirantes.aspirantes.Repository.NotificacionesRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class NotificacionesService {

    private final NotificacionesRepository notificacionesRepository;

    public NotificacionesService(NotificacionesRepository notificacionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
    }

    public List<Notificaciones> obtenerNotificaciones(){
        return notificacionesRepository.findAll();
    }

    public Notificaciones obtenerNotificacionPorId(Long id){
        Notificaciones notificacion = notificacionesRepository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Notificación con id: " + id + " no encontrada"));

        return notificacion;
    }

    public Notificaciones guardarNotificacion(Notificaciones notificacion){
        notificacion.setFechaCreacion(new Date());
        return notificacionesRepository.save(notificacion);
    }

    public Notificaciones actualizarNotificacion(Long id, Notificaciones notificacion){
        notificacion.setId(id);
        return notificacionesRepository.save(notificacion);
    }

    public void eliminarNotificacion(Long id){
        notificacionesRepository.deleteById(id);
    }

    public String buscarContenidoNotificacion(Long id){
        return notificacionesRepository.buscarContenidoNoficicacion(id);
    }
}
