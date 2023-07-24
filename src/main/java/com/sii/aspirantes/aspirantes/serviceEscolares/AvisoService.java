package com.sii.aspirantes.aspirantes.serviceEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AvisoEstudianteDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudianteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AvisoEstudianteRelacionRepositoryEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AvisoRepositoryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AvisoService {

  @Autowired
  private AvisoEstudianteRelacionRepositoryEs avisoEstudianteRelacionRepository;


  @Autowired
  private AvisoEstudianteRelacionServiceEs avisoEstudianteRelacionService;
  @Autowired
  private AvisoRepositoryEs avisoRepository;

  public AvisoService(AvisoRepositoryEs avisoRepository) {
    this.avisoRepository = avisoRepository;
  }

  public AvisoEstudianteEs enviarMensaje(String mensaje, Date vigencia) {
    AvisoEstudianteEs nuevoMensaje = new AvisoEstudianteEs();
    nuevoMensaje.setMensaje(mensaje);
    nuevoMensaje.setVigencia(vigencia);

    return avisoRepository.save(nuevoMensaje);

  }

  public List<AvisoEstudianteDTO> buscarAvisosVigentesPorAlumno(String noDeControl){
    return avisoRepository.buscarAvisosVigentesPorAlumno(noDeControl);
  }
}
