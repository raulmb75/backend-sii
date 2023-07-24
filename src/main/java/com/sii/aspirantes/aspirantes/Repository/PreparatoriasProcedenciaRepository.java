package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.PreparatoriasProcedencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreparatoriasProcedenciaRepository extends JpaRepository<PreparatoriasProcedencia, String> {

    //Buscar por entidad federativa
    @Query(value = "FROM PreparatoriasProcedencia WHERE entidadFederativa = ?1")
    List<PreparatoriasProcedencia> buscarPreparatoriasPorEntidadFederativa(Integer entidadFederativa);
    //Buscar por municipio
    @Query(value = "FROM PreparatoriasProcedencia WHERE municipio = ?1")
    List<PreparatoriasProcedencia> buscarPreparatoriasPorMunicipio(String municipio);
    //Buscar por servicio
    @Query(value = "FROM PreparatoriasProcedencia WHERE servicio = ?1")
    List<PreparatoriasProcedencia> buscarPreparatoriasPorServicio(String servicio);
    //Buscar por sostenimiento
    @Query(value = "FROM PreparatoriasProcedencia WHERE sostenimiento = ?1")
    List<PreparatoriasProcedencia> buscarPreparatoriasPorSostenimiento(String sostenimiento);

}
