package com.sii.aspirantes.aspirantes.serviceEscolares;

;
import com.sii.aspirantes.aspirantes.entityEscolares.PersonalEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.PersonalRepositoryEs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceEs {

  private final PersonalRepositoryEs personalRepository;

  public PersonalServiceEs(PersonalRepositoryEs personalRepository){
    this.personalRepository = personalRepository;
  }

  public List<PersonalEs> buscarTodos(){
    return personalRepository.findAll();
  }


  public PersonalEs guardarDatosEmpleado(PersonalEs datosEmpleado){
    return personalRepository.save(datosEmpleado);
  }

  public void eliminarEmpleado(String rfc){
    personalRepository.deleteById(rfc);
  }

  public List<PersonalEs> buscarPersonalPorClaveArea(String claveArea){
    return personalRepository.buscarPersonalPorClaveArea(claveArea);
  }

  public List<PersonalEs> buscarPersonalPorLugarNacimiento(Integer lugarNacimiento){
    return personalRepository.buscarPersonalPorLugarNacimiento(lugarNacimiento);
  }

  public List<PersonalEs> buscarPersonalPorGradoMaxEstudios(String gradoMaxEstudios){
    return personalRepository.buscarPersonalPorGradoMaxEstudios(gradoMaxEstudios);
  }
  public List<PersonalEs> buscarPersonalPorSexo(Character sexo){
    return personalRepository.buscarPersonalPorSexo(sexo);
  }
  public List<PersonalEs> buscarPersonalPorLocalidad(String localidad){
    return personalRepository.buscarPersonalPorLocalidad(localidad);
  }
  public List<PersonalEs> buscarPersonalPorStatusEmpleado(String statusEmpleado){
    return personalRepository.buscarPersonalPorStatusEmpleado(statusEmpleado);
  }
}
