package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.CarreraEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepositoryEs extends JpaRepository <CarreraEs, Integer> {

  @Query(value = "FROM Carrera WHERE nivelEscolar = ?1")
  List<CarreraEs> buscarCarreraPorNivelEscolar(Character nivelEscolar);
  @Query(value = "FROM Carrera WHERE nombreCarrera = ?1")
  List<CarreraEs> buscarCarreraPorNombre(String carrera);

  @Query(value = "FROM Carrera WHERE creditosTotales = ?1")
  List<CarreraEs> buscarCarreraPorCreditos(int creditos);

}
