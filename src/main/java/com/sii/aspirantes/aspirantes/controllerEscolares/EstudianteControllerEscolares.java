package com.sii.aspirantes.aspirantes.controllerEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AlumnosInscritosDTO;
import com.sii.aspirantes.aspirantes.dtoEscolares.RequesAspirante;
import com.sii.aspirantes.aspirantes.dtoEscolares.RequesEstudiante;
import com.sii.aspirantes.aspirantes.entityEscolares.AspiranteEs;
import com.sii.aspirantes.aspirantes.entityEscolares.EstudianteEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.AspiranteRepositoryEs;
import com.sii.aspirantes.aspirantes.repositoryEscolares.EstudianteRepositoryEs;
import com.sii.aspirantes.aspirantes.serviceEscolares.EstudianteServiceEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value = "/escolares/estudiante")
public class EstudianteControllerEscolares {

  @Autowired
  private EstudianteRepositoryEs estudianteRepository;


  @Autowired
  private AspiranteRepositoryEs aspiranteRepository;

  @Autowired
  private EstudianteServiceEs estudianteService;

  @PostMapping("/crear/alumno")
  public ResponseEntity<List<EstudianteEs>> crearAlumno(@RequestBody RequesAspirante.Curp request) {
    try{
     /* if(Utils.validarCurp(request.getCurp())==false){
        System.out.println("no se crea");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
*/
        AspiranteEs result = aspiranteRepository.findByCurp(request.getCurp());
      if(result == null){
        System.out.println("ee");
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }

      System.out.println(result);
      EstudianteEs estudiante = new EstudianteEs();

      estudiante.setCurpAlumno(result.getCurp());
      estudiante.setApellidoPaterno(result.getApellidoPaterno());
      estudiante.setApellidoMaterno(result.getApellidoMaterno());
      estudiante.setNombreAlumno(result.getNombreAspirante());
      estudiante.setCarrera(result.getCarreraOp1());
      estudiante.setCiudadProcedencia(result.getZonaProcedencia());
      estudiante.setClaveServicioMedico(result.getClavePreparatoria());
      estudiante.setFechaNacimiento(result.getFechaNacimiento());
      estudiante.setSexo(result.getGenero());
      estudiante.setEstadoCivil(result.getEstadoCivil());
      estudiante.setEscuelaProcedencia(result.getClavePreparatoria());
      estudiante.setEntidadProcedencia(result.getEspecifiqueZonaProcedencia());
      estudiante.setCiudadProcedencia(result.getZonaProcedencia());
      estudiante.setCorreoElectronico(result.getCorreoElectronico());
      //estudiante.setPeriodoIngresoIt(result.getPeriodo());



      estudiante.setFechaNacimiento(result.getFechaNacimiento());

      return new ResponseEntity(estudianteService.create(estudiante), HttpStatus.OK);

    }catch (Exception e){
      System.out.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }

  }
  @PostMapping("/asignar/nocontrol")
  public List<EstudianteEs> asignarNoControl(@RequestBody RequesEstudiante request) {
    System.out.println(request);
    try {

      Integer reticula = request.getReticula();
      String periodoIngreso  = request.getPeriodoIngresoIT();
      Long consecutivoInicial = request.getConsecutivoInicial();
      String tipoIngreso = request.getTipoIngreso();
      String añoPreinscripcion = request.getAñoPreInscripcion();

      System.out.println(reticula);
      System.out.println(periodoIngreso);

      List<EstudianteEs> datosEstudiantes = estudianteService.buscarEstudiantesAsignarNoControl(reticula, periodoIngreso);
      System.out.println(datosEstudiantes);
      for (EstudianteEs estudiante : datosEstudiantes) {
        // body of loop
        //if (estudiante.getNoDeControl() == null) {
          estudiante.setNoDeControl(String.valueOf(consecutivoInicial));
          consecutivoInicial++;
          estudianteRepository.save(estudiante);
        //}
      }


      return datosEstudiantes;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  @GetMapping("/obtener/{noDeControl}")
  public EstudianteEs obtenerEstudiante(@PathVariable String noDeControl) {
    return estudianteService.findByNoDeControl(noDeControl);
  }
  @GetMapping("/obtener/todos")
  public List<EstudianteEs> getAllEstudiantes() {
    return estudianteService.getAllEstudiantes();
  }

  @PutMapping("/estudiante/{noDeControl}/estatus")
  public void cambiarEstatusEstudiante(@PathVariable String noDeControl, @RequestBody RequesEstudiante.EstatusAlumno request){
    System.out.println(request);

      String estatus = request.getEstatusAlumno();
      estudianteService.cambiarEstatusEstudiante(noDeControl, estatus);

  }

 /* @GetMapping("/estudiante/{reticula}")
  public List<Estudiante> buscarPorCarrera(@PathVariable Integer reticula) {
    return estudianteService.buscarPorCarrera(reticula);
  }*/

  @GetMapping(value = "/carrera/{reticula}")
  public List<EstudianteEs> mostrarEstudiantesPorCarrera(@PathVariable Integer reticula){
    return estudianteService.buscarEstudiantesPorCarrera(reticula);
  }

  @GetMapping(value = "/periodo/{periodo}")
  public List<EstudianteEs> mostrarEstudiantesPorPeriodo(@PathVariable String periodo){
    return estudianteService.buscarEstudiantesPorPeriodo(periodo);
  }

  @GetMapping(value = "/nombre/nip/{noDeControl}")
  public EstudianteEs mostrarNombreYNip(@PathVariable String noDeControl){
    return estudianteService.mostrarNipYNombre(noDeControl);
  }

  @GetMapping(value = "/obtener/alumnos/inscritos/{reticula}/{periodo}")
  public List<AlumnosInscritosDTO> buscarAlumnosInscritos(@PathVariable Integer reticula, @PathVariable String periodo){
    System.out.println(reticula + periodo);
    return estudianteService.buscarAlumnosInscritos(reticula, periodo);
  }


}
