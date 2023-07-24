package com.sii.aspirantes.aspirantes.serviceEscolares;


import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudianteEs;
import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudiantePK;
import com.sii.aspirantes.aspirantes.entityEscolares.EstudianteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AvisoEstudianteRelacionRepositoryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvisoEstudianteRelacionServiceEs {

  @Autowired
  private AvisoEstudianteRelacionRepositoryEs avisoEstudianteRelacionRepository;

 /* public AvisoEstudiantePK insertarRelacion(Long idAviso, String idEstudiante){
    AvisoEstudiantePK guardarDatos = new AvisoEstudiantePK();
    guardarDatos.setIdAviso(idAviso);
    guardarDatos.setIdEstudiante(idEstudiante);

    return avisoEstudianteRelacionRepository.save(guardarDatos);
  }*/
  public AvisoEstudiantePK insertarRelacion(AvisoEstudianteEs idAviso, EstudianteEs idEstudiante){
    AvisoEstudiantePK guardarDatos = new AvisoEstudiantePK();
    guardarDatos.setIdAviso(idAviso);
    guardarDatos.setIdEstudiante(idEstudiante);

    return avisoEstudianteRelacionRepository.save(guardarDatos);
  }




}
