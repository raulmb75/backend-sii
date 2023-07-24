package com.sii.aspirantes.aspirantes.serviceEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.MateriaEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.MateriaRepositoryEs;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MateriaServiceEs {

  private final MateriaRepositoryEs materiaRepository;

  public MateriaServiceEs(MateriaRepositoryEs materiaRepository) {
    this.materiaRepository = materiaRepository;
  }

  public List<MateriaEs> buscarTodasMaterias(){
    return materiaRepository.findAll();
  }

  public MateriaEs buscarMateriaPorClave(String claveMateria){
    MateriaEs materia =  materiaRepository.findById(claveMateria)
      .orElseThrow(()->
        new EntityNotFoundException("Materia con clave " + claveMateria + " no registrada"));

    return materia;
  }

  public MateriaEs registrarMateria(MateriaEs materia){
    return materiaRepository.save(materia);
  }

  public MateriaEs actualizarDatosMateria(String claveMateria, MateriaEs materia){
    MateriaEs materiaRegistrada = materiaRepository.findById(claveMateria).orElse(null);

    if(materiaRegistrada != null) {
      return materiaRepository.save(materia);
    }

    throw new EntityNotFoundException(String.format("Materia con clave %s no registrada", claveMateria));
  }

  public void eliminarMateria(String claveMateria){
    materiaRepository.deleteById(claveMateria);
  }

  public List<MateriaEs> buscarMateriaPorNivelEscolar(Character nivelEscolar){
    return materiaRepository.buscarMateriaPorNivelEscolar(nivelEscolar);
  }

  public List<MateriaEs> buscarMateriaPorDepartamento(String claveArea){
    return materiaRepository.buscarMateriaPorDepartamento(claveArea);
  }

  public List<MateriaEs> buscarMateriaPorTipo(Integer tipoMateria){
    return materiaRepository.buscarMateriaPorTipo(tipoMateria);
  }
}
