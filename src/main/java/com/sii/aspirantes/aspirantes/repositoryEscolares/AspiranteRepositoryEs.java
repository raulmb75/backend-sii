package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.dtoEscolares.FichaAspiranteDTO;
import com.sii.aspirantes.aspirantes.entityEscolares.AspiranteEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AspiranteRepositoryEs extends JpaRepository <AspiranteEs, Long> {

  AspiranteEs findByCurp(String curp);

 /* @Query(value = "SELECT e.no_de_control AS noDeControl, e.nombre_alumno AS nombreAlumno, e.apellido_paterno AS apellidoPaterno, " +
    "e.apellido_materno AS apelliodMaterno, e.sexo, e.creditos_aprobados AS creditosAprobados, e.creditos_aprobados * 100 / e.creditos_cursados AS porcentajeAprobado," +
    " e.creditos_cursados AS creditosCursados, "+
    "e.creditos_cursados * 100 / 260 AS porcentajeCursado, "+
    "e.promedio_aritmetico_acumulado AS promedioAritmeticoAcumulado  FROM estudiante AS e " +
    "WHERE e.reticula = ?1 AND e.periodo_ingreso_it = ?2 " +
    "ORDER BY e.nombre_alumno ASC ", nativeQuery = true)
*/

  @Query(value = " SELECT a.no_solicitud AS noSolicitud, a.nombre_aspirante AS nombreAspirante, a.apellido_paterno AS apellidoPaterno, " +
    "a.apellido_materno AS apellidoMaterno, a.colonia_aspirante AS coloniaAspirante, a.codigo_postal AS codigoPostal, a.entidad_federativa AS entidadFederativa, " +
    "a.municipio AS municipio, a.clave_preparatoria AS clavePreparatoria, a.entidad_federativa_prepa AS entidadFederativaPrepa, a.carrera_opcion_1 AS carreraOp1, " +
    "a.carrera_opcion_2 AS carreraOp2 FROM sol_ficha_examen AS a WHERE a.curp=?1 ", nativeQuery = true)
  List<FichaAspiranteDTO>buscarDatosFichaAspirante(String curp);

}
