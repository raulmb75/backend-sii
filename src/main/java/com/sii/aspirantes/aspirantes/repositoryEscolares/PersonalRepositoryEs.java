package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.PersonalEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepositoryEs extends JpaRepository<PersonalEs, String> {

  @Query(value = "FROM Personal p WHERE p.claveArea.claveArea = ?1")
  List<PersonalEs> buscarPersonalPorClaveArea(String claveArea);
  @Query(value = "FROM Personal WHERE lugarNacimiento = ?1")
  List<PersonalEs> buscarPersonalPorLugarNacimiento(Integer lugarNacimiento);
  @Query(value = "FROM Personal WHERE gradoMaximoEstudios = ?1")
  List<PersonalEs> buscarPersonalPorGradoMaxEstudios(String gradoMaxEstudios);
  @Query(value = "FROM Personal WHERE sexoEmpleado = ?1")
  List<PersonalEs> buscarPersonalPorSexo(Character sexo);
  @Query(value = "FROM Personal WHERE localidad = ?1")
  List<PersonalEs> buscarPersonalPorLocalidad(String localidad);
  @Query(value = "FROM Personal WHERE estatusEmpleado = ?1")
  List<PersonalEs> buscarPersonalPorStatusEmpleado(String statusEmpleado);

}
