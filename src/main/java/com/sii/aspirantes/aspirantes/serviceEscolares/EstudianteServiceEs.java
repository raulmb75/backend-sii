package com.sii.aspirantes.aspirantes.serviceEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AlumnosInscritosDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.EstudianteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.EstudianteRepositoryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceEs {

  @Autowired
  private EstudianteRepositoryEs estudianteRepository;
  public List<EstudianteEs> buscarEstudiantesAsignarNoControl(Integer reticula, String periodoIngresoIt) {
    List<EstudianteEs> estudiantes = estudianteRepository.buscarEstudiantesAsignarNoControl(reticula, periodoIngresoIt);
    return estudiantes;
  }

  public EstudianteEs create(EstudianteEs estudiante) {
    return estudianteRepository.save(estudiante);
  }

  public EstudianteEs findByNoDeControl(String noDeControl) {
    return estudianteRepository.findByNoDeControl(noDeControl);
  }

  public List<EstudianteEs> getAllEstudiantes() {
    return estudianteRepository.findAll();
  }

  public void cambiarEstatusEstudiante(String noDeControl, String estatusAlumno ){
    EstudianteEs estudiante = estudianteRepository.findByNoDeControl(noDeControl);
    estudiante.setEstatusAlumno("EGR");
    estudianteRepository.save(estudiante);
    System.out.println(estatusAlumno);
  }

  /*public List<Estudiante> buscarPorCarrera(Integer reticula) {
    return estudianteRepository.findByCarera(reticula);
  }*/

  public List<EstudianteEs> buscarEstudiantesPorCarrera(Integer reticula){
    return estudianteRepository.buscarEstudiantesPorCarrera(reticula);
  }

  public EstudianteEs mostrarNipYNombre(String noDeControl){
    EstudianteEs data = new EstudianteEs();
    data.getNombreAlumno();
    data.getNip();
    return estudianteRepository.findByNoDeControl(noDeControl);
  }

  public List<EstudianteEs> buscarEstudiantesPorPeriodo(String periodo){
    return estudianteRepository.buscarEstudiantesPorPeriodo(periodo);
  }

  public List<AlumnosInscritosDTO> buscarAlumnosInscritos(Integer reticula, String periodo){
    return estudianteRepository.buscarEstudiantesInscritos(reticula,periodo);
  }


}
