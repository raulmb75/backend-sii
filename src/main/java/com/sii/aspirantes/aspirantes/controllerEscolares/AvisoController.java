package com.sii.aspirantes.aspirantes.controllerEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AvisoEstudianteDTO;
import com.sii.aspirantes.aspirantes.dtoEscolares.RequesAviso;
import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudianteEs;
import com.sii.aspirantes.aspirantes.entityEscolares.EstudianteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AvisoEstudianteRelacionRepositoryEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AvisoRepositoryEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.AvisoEstudianteRelacionServiceEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.AvisoService;
import com.sii.aspirantes.aspirantes.serviceEscolares.EstudianteServiceEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@Controller
@RequestMapping(value="/escolares/avisos")
public class AvisoController {


  private AvisoService avisoService;

  @Autowired
  private AvisoEstudianteRelacionRepositoryEs avisoEstudianteRelacionRepository;

  @Autowired
  private AvisoRepositoryEs avisoRepository;
  @Autowired
  private AvisoEstudianteRelacionServiceEs avisoEstudianteRelacionService;
  @Autowired
  private EstudianteServiceEs estudianteService;

  public AvisoController(AvisoService avisoService) {
    this.avisoService = avisoService;
  }

 /*@PostMapping("/enviar/mensaje")
  public AvisoEstudiante saveMessage(@RequestBody RequesAviso requesAviso) {
    System.out.println(requesAviso.getNoControl() + requesAviso.getVigencia() + requesAviso.getMensaje());
    String[] noControl = requesAviso.getNoControl().split(",");

    AvisoEstudiante aviso = avisoService.enviarMensaje(requesAviso.getMensaje(), requesAviso.getVigencia());
    for (int i = 0; i < noControl.length; i++) {
      Estudiante estudiante =
      avisoEstudianteRelacionService.insertarRelacion(aviso, noControl[i]);
    }
    return aviso;

  }*/

  @PostMapping("/enviar/mensaje")
  public AvisoEstudianteEs saveMessage(@RequestBody RequesAviso requesAviso) {
    System.out.println(requesAviso.getNoControl() + requesAviso.getVigencia() + requesAviso.getMensaje());
    String[] noControl = requesAviso.getNoControl().split(",");
    EstudianteEs estudiante;
    AvisoEstudianteEs aviso = avisoService.enviarMensaje(requesAviso.getMensaje(), requesAviso.getVigencia());
    for (int i = 0; i < noControl.length; i++) {
      estudiante = estudianteService.findByNoDeControl(noControl[i]);
      avisoEstudianteRelacionService.insertarRelacion(aviso,estudiante);
    }
    return aviso;
  }



 @GetMapping("/obtener/avisos/vigentes/{noDeControl}")
  public List<AvisoEstudianteDTO> buscarAvisosVigentesPorAlumno(@PathVariable String noDeControl){
    //System.out.println(noDeControl);
    return avisoService.buscarAvisosVigentesPorAlumno(noDeControl);
  }


}
