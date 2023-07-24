package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.AvisoEstudianteDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudianteEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepositoryEs extends JpaRepository <AvisoEstudianteEs, Long> {

 /*@Query(value = "SELECT ae.id, ae.mensaje, ae.vigencia " +
    "FROM AvisoEstudiante as ae " +
    "inner join AvisoEstudiantePK as aei on ae.id = aei.idAviso " +
    "inner join Estudiante as e on e.noDeControl = aei.idEstudiante " +
    "where date(ae.vigencia)  > now() AND e.noDeControl = ?1")
  List<AvisoEstudiante> buscarAvisosVigentesPorAlumno(String noDeControl);*/

 /* SELECT ae.id,
  ae.mensaje,
  ae.fecha_caducidad AS aviso,
  FROM public.aviso_estudiante AS ae
  INNER JOIN aviso_estudiante_intermedio AS aei ON ae.id = aei.id_aviso
  INNER JOIN estudiante AS e ON e.no_de_control = aei.id_estudiante
  WHERE date(ae.fecha_caducidad)  > now() AND e.no_de_control=23680001';*/

  /*@Query(value = "SELECT ae.idAviso.id, ae.idAviso.mensaje, ae.idAviso.vigencia " +
    "FROM AvisoEstudiantePK AS ae " +
    "where date(ae.idAviso.vigencia)  > now() AND ae.idEstudiante.noDeControl = ?1")*/

 @Query(value = " SELECT ae.id, " +
  "ae.mensaje, "+
  "ae.fecha_caducidad AS vigencia "+
 " FROM public.aviso_estudiante AS ae "+
  "INNER JOIN aviso_estudiante_intermedio AS aei ON ae.id = aei.id_aviso "+
  "INNER JOIN estudiante AS e ON e.no_de_control = aei.id_estudiante "+
  "WHERE date(ae.fecha_caducidad)  > now() AND e.no_de_control=?1 ", nativeQuery = true)
  List<AvisoEstudianteDTO> buscarAvisosVigentesPorAlumno(String noDeControl);



}
