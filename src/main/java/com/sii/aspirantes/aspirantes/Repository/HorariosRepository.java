package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Horarios;
import com.sii.aspirantes.aspirantes.dto.HorarioSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Integer> {
    //Búsqueda por grupo
    @Query(value = "FROM Horarios h WHERE h.periodo.periodo = ?1 AND h.idGrupo.idGrupo = ?2")
    List<Horarios> buscarGruposPorGrupo(String periodo, Integer idGrupo);
    //Búsqueda por materia
    @Query(value = "FROM Horarios h WHERE h.periodo.periodo = ?1 AND h.materia.materia = ?2")
    List<Horarios> buscarGruposPorMateria(String periodo, String materia);
    //Búsqueda por personal

    //Búsqueda por combinada - Buscar por: materia (defecto)
    @Query(value = "FROM Horarios h " +
            "WHERE h.periodo.periodo = ?1 " +
            "AND h.materia.materia = ?2 " +
            "AND h.grupo = ?3 " +
            "AND h.rfc.rfc = ?4")
    List<Horarios> busquedaCompleta(String periodo, String materia, String grupo, String rfc);

}
