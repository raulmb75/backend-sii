package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.MateriaEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaRepositoryEs extends JpaRepository <MateriaEs, String> {


  @Query(value = "FROM Materia m WHERE m.nivelEscolar.nivelEscolar = ?1")
  List<MateriaEs> buscarMateriaPorNivelEscolar(Character nivelEscolar);

  @Query(value = "FROM Materia m WHERE m.claveArea.claveArea = ?1")
  List<MateriaEs> buscarMateriaPorDepartamento(String claveArea);

  @Query(value = "FROM Materia m WHERE m.tipoMateria.tipoMateria = ?1")
  List<MateriaEs> buscarMateriaPorTipo(Integer tipoMateria);

}
