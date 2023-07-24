package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    @Query(value = "FROM Notificaciones WHERE periodo = ?1")
    List<Notificaciones> buscarNotificacionesPorPeriodo(String periodo);

    @Query(value = "FROM Notificaciones WHERE asunto = ?1 AND periodo = ?2")
    Notificaciones buscarNotificacionPorAsunto(String asunto, String periodo);

    @Query(value = "SELECT contenido FROM notificaciones WHERE id = ?1", nativeQuery = true)
    String buscarContenidoNoficicacion(Long id);
}
