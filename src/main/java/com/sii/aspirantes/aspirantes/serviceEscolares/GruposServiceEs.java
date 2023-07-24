package com.sii.aspirantes.aspirantes.serviceEscolares;


import com.sii.aspirantes.aspirantes.entityEscolares.GruposEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.GruposRepositoryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GruposServiceEs {

  @Autowired
  private GruposRepositoryEs gruposRepository;

  public List<GruposEs> buscarGruposPorCarrera(Integer reticula){
    return gruposRepository.buscarGruposPorCarrera(reticula);
  }

  public List<GruposEs> buscarGruposPorCarreraYperiodo(Integer reticula, String periodo){
    return gruposRepository.buscarGruposPorCarreraYperiodo(reticula,periodo);
  }
}
