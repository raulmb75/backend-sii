package com.sii.aspirantes.aspirantes.serviceEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.CarreraEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.CarreraRepositoryEs;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarreraServiceEs {
  private final CarreraRepositoryEs carreraRepository;

  public CarreraServiceEs(CarreraRepositoryEs carreraRepository) {
    this.carreraRepository = carreraRepository;
  }

  public List<CarreraEs> buscarTodasCarreras(){
    return carreraRepository.findAll();
  }

  public CarreraEs buscarCarreraPorReticula(Integer reticula){
    CarreraEs carrera = carreraRepository.findById(reticula)
      .orElseThrow(()->
        new EntityNotFoundException("Carrera con retícula " + reticula + " no encontrada"));

    return carrera;
  }

  public List<CarreraEs> buscarCarreraPorNivelEscolar(Character nivelEscolar){
    return carreraRepository.buscarCarreraPorNivelEscolar(nivelEscolar);
  }

  public List<CarreraEs> buscarCarreraPorNombre(String nombre){
    return carreraRepository.buscarCarreraPorNombre(nombre);
  }

  public List<CarreraEs> buscarCarreraPorCreditosTotales(int creditos){
    return carreraRepository.buscarCarreraPorCreditos(creditos);
  }
}
