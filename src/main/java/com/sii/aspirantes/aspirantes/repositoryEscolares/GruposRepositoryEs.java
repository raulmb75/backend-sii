package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.GruposEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GruposRepositoryEs extends JpaRepository<GruposEs, Integer> {

  //buscar por reticula
  @Query(value = "FROM Grupos g WHERE g.reticula.reticula = ?1")
  List<GruposEs> buscarGruposPorCarrera(Integer reticula);

  //buscar por reticula y periodo
  @Query(value = "FROM Grupos AS g WHERE  g.reticula.reticula = ?1 AND g.periodo.periodo = ?2 ")
  List<GruposEs> buscarGruposPorCarreraYperiodo(Integer reticula, String periodo);
}
