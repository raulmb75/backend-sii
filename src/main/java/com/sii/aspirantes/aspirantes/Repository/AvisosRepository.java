package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Avisos;
import com.sii.aspirantes.aspirantes.dto.AvisoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisosRepository extends JpaRepository<Avisos,Long> {

    @Query(value = "SELECT id, titulo, contenido, seccion, fecha_inicio AS fechaInicio, fecha_vigencia AS fechaVigencia " +
            "FROM avisos WHERE usuario = ?1 ", nativeQuery = true)
    List<AvisoResponse> buscarAvisosPorUsuario(String usuario);

    @Query(value = "SELECT id, titulo, contenido, seccion, fecha_inicio AS fechaInicio, fecha_vigencia AS fechaVigencia " +
            "FROM avisos WHERE usuario = ?1 " +
            "AND id = ?2 ", nativeQuery = true)
    AvisoResponse buscarAvisosPorUsuarioId(String usuario, Long id);

}
