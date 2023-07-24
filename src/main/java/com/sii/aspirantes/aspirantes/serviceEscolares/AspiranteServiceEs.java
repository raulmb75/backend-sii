package com.sii.aspirantes.aspirantes.serviceEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.FichaAspiranteDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.AspiranteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AspiranteRepositoryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspiranteServiceEs {

  @Autowired
  private AspiranteRepositoryEs aspiranteRepository;

  public List<AspiranteEs> getAllAspirantes() {
    return aspiranteRepository.findAll();
  }

  public AspiranteEs obtenerAspirantePorCurp(String curp){
    return aspiranteRepository.findByCurp(curp);
  }

  public List<FichaAspiranteDTO> buscarDatosFichaAspirante(String curp){
    return aspiranteRepository.buscarDatosFichaAspirante(curp);
  }
}
