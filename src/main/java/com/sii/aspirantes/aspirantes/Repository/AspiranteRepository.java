package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.dto.aspirante.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AspiranteRepository extends JpaRepository<Aspirante,Long> {

    @Query(value = "FROM EntidadFederativa")
    public List<EntidadFederativa> mostrarEntidadFederativa(); //No se usará posiblemente
    @Query(value = "FROM Carrera")
    public List<Carrera> mostrarCarreras();
    @Query(value = "FROM Aspirante WHERE curp = ?1")
    public Optional<Aspirante> validarAspirantePorCurp(String curp);
    @Query("FROM Aspirante WHERE periodo = ?1")
    public List<Aspirante> obtenerAspirantePorPeriodo(Integer periodo);
    @Query(value = "FROM Aspirante WHERE periodo = ?1 AND noRecibo > 0")
    List<Aspirante> obtenerFichaAspirantesPorPeriodo(Integer periodo);
    @Query(value = "FROM Aspirante WHERE periodo = ?1 AND estatusPago = ?2")
    List<Aspirante> obtenerAspirantesPorEstatusPago(Integer periodo, String estatusPago);
    @Query(value = "FROM Aspirante WHERE periodo = ?1 AND aceptado = ?2")
    List<Aspirante> obtenerAspirantesPorEstatusAceptado(Integer periodo, boolean aceptado);
    @Query(value = "FROM Aspirante WHERE periodo = ?1 AND estatusExamen = ?2")
    List<Aspirante> obtenerAspirantesPorEstatusExamen(Integer periodo, String estatusExamen);
    @Query(value = "SELECT COUNT(*) FROM sol_ficha_examen " +
            "WHERE date(fecha_registro) > ?1 " +
            "AND date(fecha_registro) < ?2 " +
            "AND periodo = ?3", nativeQuery = true)
    Integer contarSolicitudesPorFecha(Date fechaInicial, Date fechaFinal, Integer periodo);

    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE no_solicitud = ?1", nativeQuery = true)
    String obtenerCorreoAspirantePorNoSolicitud(Long noSolicitud);
    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE periodo = ?1", nativeQuery = true)
    List<String> obtenerCorreoAspirantes(Integer periodo);
    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE periodo = ?1 AND no_recibo > 0", nativeQuery = true)
    List<String> obtenerCorreoAspiranteFichas(Integer periodo);
    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE periodo = ?1 AND estatus_pago = ?2", nativeQuery = true)
    List<String> obtenerCorreoAspirantesPorEstatusPago(Integer periodo, String estatusPago);
    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE periodo = ?1 AND aceptado = ?2", nativeQuery = true)
    List<String> obtenerCorreoAspirantesPorEstatusAceptado(Integer periodo, boolean aceptado);
    @Query(value = "SELECT correo_electronico FROM sol_ficha_examen WHERE periodo = ?1 AND estatus_examen = ?2", nativeQuery = true)
    List<String> obtenerCorreoAspirantesPorEstatusExamen(Integer periodo, String estatusExamen);

    //Búsqueda por municipio
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaDTO(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, " +
            "a.aula, a.carreraOp1.nombreCarrera, a.curp, a.telefono, a.correoElectronico) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.noRecibo > 0 " +
            "AND a.entidadFederativa.entidadFederativa = ?2 " +
            "AND a.municipio = ?3 " +
            "ORDER BY a.apellidoPaterno")
    List<AspiranteFichaDTO> obtenerFichasAspirantesPorMunicipio(Integer periodo, Integer estado, String municipio);

    //Obtiene conteo de fichas de aspirantes por municipio
    @Query(value = "SELECT municipio, COUNT(*) AS conteo FROM sol_ficha_examen " +
            "WHERE periodo = ?1 AND no_recibo > 0 " +
            "GROUP BY municipio ORDER BY conteo DESC", nativeQuery = true)
    List<ConteoFichasMunicipiosDTO> contarFichasAspirantesPorMunicipio(Integer periodo);

    //Lista de solicitudes de aspirantes registrados - Lista de solicitudes de aspirantes
    @Query(value = "SELECT no_solicitud AS noSolicitud, " +
            "apellido_paterno AS apellidoPaterno, " +
            "apellido_materno AS apellidoMaterno, " +
            "nombre_aspirante AS nombreAspirante, " +
            "nip, " +
            "curp, " +
            "correo_electronico AS correoElectronico, " +
            "telefono, " +
            "fecha_nacimiento AS fechaNacimiento, " +
            "genero " +
            "FROM sol_ficha_examen WHERE periodo = ?1", nativeQuery = true)
    List<AspiranteDTO> getSolicitudesAspirantesPorPeriodo(Integer periodo);

    //Lista de solicitudes de aspirantes registrados - Lista de solicitudes de aspirantes
    @Query(value = "SELECT no_solicitud AS noSolicitud, " +
            "apellido_paterno AS apellidoPaterno, " +
            "apellido_materno AS apellidoMaterno, " +
            "nombre_aspirante AS nombreAspirante, " +
            "nip, " +
            "curp, " +
            "correo_electronico AS correoElectronico, " +
            "telefono, " +
            "fecha_nacimiento AS fechaNacimiento, " +
            "genero " +
            "FROM sol_ficha_examen WHERE periodo = ?1 AND no_recibo > 0", nativeQuery = true)
    List<AspiranteDTO> getSolicitudesFichasAspirantesPorPeriodo(Integer periodo);

    //Lista de fichas de aspirantes registrados - Lista de fichas de aspirantes
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaDTO(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, " +
            "a.aula, a.carreraOp1.nombreCarrera, a.curp, a.telefono, a.correoElectronico) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.noRecibo > 0 ORDER BY a.apellidoPaterno")
    List<AspiranteFichaDTO> obtenerFichaAspirantesTodasCarrerasAula(Integer periodo);

    //Lista de fichas de aspirantes registrados - Lista de fichas de aspirantes POR CARRERA
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaDTO(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, " +
            "a.aula, a.carreraOp1.nombreCarrera, a.curp, a.telefono, a.correoElectronico) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.noRecibo > 0 " +
            "AND a.carreraOp1.reticula = ?2 ORDER BY a.apellidoPaterno")
    List<AspiranteFichaDTO> obtenerFichaAspirantesPorCarreraAulaV2(Integer periodo, Integer carrera);

    //Estadística de fichas  de aspirantes por carrera
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspirantesPorCarreraDTO(a.carreraOp1.nombreCarrera, COUNT(a.noSolicitud)) " +
            "FROM Aspirante AS a " +
            "GROUP BY a.carreraOp1.nombreCarrera")
    List<AspirantesPorCarreraDTO> fichasAspirantesCarrera();

    //Lista de fichas de aspirantes por carrera
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaCarerraDTO(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, a.genero, a.clavePreparatoria.nombrePreparatoria, a.correoElectronico, a.telefono, a.curp, a.carreraOp1.nombreCarrera) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.noRecibo > 0 " +
            "AND a.carreraOp1.reticula = ?2")
    List<AspiranteFichaCarerraDTO> obtenerFichaAspirantePorCarrera(Integer periodo, Integer carrera);

    //Lista de fichas de aspirantes por carrera TODAS LAS CARRERAS
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaCarerraDTO(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, a.genero, a.clavePreparatoria.nombrePreparatoria, a.correoElectronico, a.telefono, a.curp, a.carreraOp1.nombreCarrera) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.noRecibo > 0")
    List<AspiranteFichaCarerraDTO> obtenerFichaAspiranteTodasCarreras(Integer periodo);

    //Comparativo de solicitudes de aspirantes

    @Query(value = "SELECT c.nombre_carrera as nombreCarrera, " +
            "COUNT(1) FILTER (WHERE s.genero = 'M') as numSolicitudesH, " +
            "COUNT(1) FILTER (WHERE s.genero = 'F') as numSolicitudesM, " +
            "COUNT(1) FILTER (WHERE s.genero = 'M' AND s.estatus_examen = 'APROBADO') as numHombresAceptados, " +
            "COUNT(1) FILTER (WHERE s.genero = 'F' AND s.estatus_examen = 'APROBADO') as numMujeresAceptadas " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c " +
            "ON s.carrera_opcion_1 = c.reticula " +
            "WHERE periodo = ?1 " +
            "GROUP BY c.nombre_carrera", nativeQuery = true)
    List<IAtencionDemandaAlumnos> getListaAtencionDemandaLicenciatura(Integer periodo);

    //Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.SeleccionFichaAspiranteRequest(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, a.carreraOp1.nombreCarrera, a.carreraOp1.reticula, a.aceptado, a.clavePreparatoria.clavePreparatoria, a.clavePreparatoria.nombrePreparatoria) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1 " +
            "AND a.carreraOp1.reticula = ?2")
    List<SeleccionFichaAspiranteRequest> getDatosFichaAspirantesPorCarrera(Integer periodo, Integer reticula);

    //Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera TODAS LAS CARRERAS
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.SeleccionFichaAspiranteRequest(" +
            "a.noRecibo, a.noSolicitud, a.nombreAspirante, a.apellidoPaterno, a.apellidoMaterno, " +
            "a.carreraOp1.nombreCarrera, a.carreraOp1.reticula, a.aceptado, " +
            "a.clavePreparatoria.clavePreparatoria, a.clavePreparatoria.nombrePreparatoria) " +
            "FROM Aspirante AS a " +
            "WHERE a.periodo = ?1")
    List<SeleccionFichaAspiranteRequest> getDatosFichaAspirantes(Integer periodo);

    @Query(value = "SELECT carrera_opcion_1 FROM sol_ficha_examen GROUP BY carrera_opcion_1", nativeQuery = true)
    List<Integer> mostrarCarrerasRegistradas();

    @Query(value = "SELECT s.no_solicitud AS noSolicitud, " +
            "s.no_recibo AS noRecibo, " +
            "s.nombre_aspirante AS nombreAspirante, " +
            "s.apellido_paterno AS apellidoPaterno, " +
            "s.apellido_materno AS apellidoMaterno, " +
            "s.genero, " +
            "c.nombre_carrera AS nombreCarrera, " +
            "s.curp, " +
            "s.correo_electronico AS correoElectronico, " +
            "s.telefono, " +
            "p.nombre_preparatoria AS nombreEscuelaProcedencia, " +
            "p.municipio, " +
            "s.promedio_general AS promedioGeneral " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c ON s.carrera_opcion_1 = c.reticula " +
            "INNER JOIN preparatorias_de_procedencia p ON s.clave_preparatoria = p.clave_preparatoria " +
            "WHERE s.periodo = ?1 AND s.no_recibo > 0", nativeQuery = true)
    List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedencia(Integer periodo);

    //Buscar fichas por preparatorias - Por estado
    @Query(value = "SELECT s.no_solicitud AS noSolicitud, " +
            "s.no_recibo AS noRecibo, " +
            "s.nombre_aspirante AS nombreAspirante, " +
            "s.apellido_paterno AS apellidoPaterno, " +
            "s.apellido_materno AS apellidoMaterno, " +
            "s.genero, " +
            "c.nombre_carrera AS nombreCarrera, " +
            "s.curp, " +
            "s.correo_electronico AS correoElectronico, " +
            "s.telefono, " +
            "p.nombre_preparatoria AS nombreEscuelaProcedencia, " +
            "p.municipio, " +
            "s.promedio_general AS promedioGeneral " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c ON s.carrera_opcion_1 = c.reticula " +
            "INNER JOIN preparatorias_de_procedencia p ON s.clave_preparatoria = p.clave_preparatoria " +
            "WHERE s.periodo = ?1 AND s.no_recibo > 0 AND p.entidad_federativa = ?2", nativeQuery = true)
    List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedenciaEstado(Integer periodo, Integer estado);

    //Buscar fichas por preparatorias - Por municipio
    @Query(value = "SELECT s.no_solicitud AS noSolicitud, " +
            "s.no_recibo AS noRecibo, " +
            "s.nombre_aspirante AS nombreAspirante, " +
            "s.apellido_paterno AS apellidoPaterno, " +
            "s.apellido_materno AS apellidoMaterno, " +
            "s.genero, " +
            "c.nombre_carrera AS nombreCarrera, " +
            "s.curp, " +
            "s.correo_electronico AS correoElectronico, " +
            "s.telefono, " +
            "p.nombre_preparatoria AS nombreEscuelaProcedencia, " +
            "p.municipio, " +
            "s.promedio_general AS promedioGeneral " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c ON s.carrera_opcion_1 = c.reticula " +
            "INNER JOIN preparatorias_de_procedencia p ON s.clave_preparatoria = p.clave_preparatoria " +
            "WHERE s.periodo = ?1 AND s.no_recibo > 0 AND p.entidad_federativa = ?2 AND p.municipio = ?3", nativeQuery = true)
    List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedenciaMunicipio(Integer periodo,Integer estado, String municipio);

    //Buscar fichas por preparatorias - Por Preparatoria específica
    @Query(value = "SELECT s.no_solicitud AS noSolicitud, " +
            "s.no_recibo AS noRecibo, " +
            "s.nombre_aspirante AS nombreAspirante, " +
            "s.apellido_paterno AS apellidoPaterno, " +
            "s.apellido_materno AS apellidoMaterno, " +
            "s.genero, " +
            "c.nombre_carrera AS nombreCarrera, " +
            "s.curp, " +
            "s.correo_electronico AS correoElectronico, " +
            "s.telefono, " +
            "p.nombre_preparatoria AS nombreEscuelaProcedencia, " +
            "p.municipio, " +
            "s.promedio_general AS promedioGeneral " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c ON s.carrera_opcion_1 = c.reticula " +
            "INNER JOIN preparatorias_de_procedencia p ON s.clave_preparatoria = p.clave_preparatoria " +
            "WHERE s.periodo = ?1 AND s.no_recibo > 0 AND p.entidad_federativa = ?2 " +
            "AND p.municipio = ?3 AND p.clave_preparatoria = ?4", nativeQuery = true)
    List<FichaPorPreparatoriaDTO> buscarFichasPorNombrePreparatoriaProcedencia(Integer periodo,
                                                                               Integer estado,
                                                                               String municipio,
                                                                               String clavePreparatoria);

    //CONTEO DE FICHAS POR CARRERAS Y PREPARATORIA ESPECÍFICA
    @Query(value = "SELECT c.nombre_carrera, " +
            "COUNT(s.clave_preparatoria) " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN carrera c ON s.carrera_opcion_1 = c.reticula " +
            "WHERE s.periodo = ?1 " +
            "AND s.clave_preparatoria = ?2 " +
            "GROUP BY c.nombre_carrera", nativeQuery = true)
    List<FichasCarrerasPrepaTotalDTO> buscarFichasPorCarrerasPreparatoria(String periodo, String clavePreparatoria);

    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.ConteoFichasPrepaDTO (" +
            "s.clavePreparatoria.nombrePreparatoria, COUNT(s.clavePreparatoria.clavePreparatoria)) " +
            "FROM Aspirante s " +
            "WHERE s.periodo = ?1 AND s.noRecibo > 0 " +
            "GROUP BY s.clavePreparatoria.nombrePreparatoria")
    List<ConteoFichasPrepaDTO> conteoFichasPorTodasCarreras(Integer periodo);

    //Conteo de fichas por carreras (todas las preparatorias)
    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.aspirante.ConteoFichasPrepaDTO (" +
            "s.clavePreparatoria.nombrePreparatoria, COUNT(s.clavePreparatoria.clavePreparatoria)) " +
            "FROM Aspirante s " +
            "WHERE s.periodo = ?1 AND s.carreraOp1.reticula = ?2 AND s.noRecibo > 0 " +
            "GROUP BY s.clavePreparatoria.nombrePreparatoria")
    List<ConteoFichasPrepaDTO> conteoFichasPorCarrera(Integer periodo, Integer reticula);

    @Query(value = "SELECT COUNT(a) FROM sol_ficha_examen a " +
            "WHERE a.periodo = ?1 " +
            "AND a.fecha_registro >= date(?2) " +
            "AND a.fecha_registro < date(?3)",
            nativeQuery = true)
    Long contarSolcitudesPorMes(Integer periodo, String fechaInicio, String fechaTermino);

    @Query(value = "SELECT COUNT(a) FROM sol_ficha_examen a " +
            "WHERE a.periodo = ?1 " +
            "AND a.fecha_registro >= date(?2) " +
            "AND a.fecha_registro < date(?3) " +
            "AND a.no_recibo >0",
            nativeQuery = true)
    Long contarFichasPorMes(Integer periodo, String fechaInicio, String fechaTermino);

    //Contar solicitudes por periodo
    @Query(value = "SELECT COUNT(s) FROM sol_ficha_examen s WHERE periodo = ?1", nativeQuery = true)
    Long contarSolicitudesPorPeriodo(Integer periodo);

    //Contar fichas por periodo
    @Query(value = "SELECT COUNT(s) FROM sol_ficha_examen s WHERE periodo = ?1 AND s.no_recibo > 0", nativeQuery = true)
    Long contarFichasPorPeriodo(Integer periodo);


    //Lista las todas las preparatorias con la cantidad de fichas que generaron dentro del ITC
    @Query(value = "SELECT p.clave_preparatoria as clavePreparatoria, " +
            "p.nombre_preparatoria as nombrePreparatoria, " +
            "COUNT(*) as cantidadFichas, " +
            "p.municipio, " +
            "p.sostenimiento " +
            "FROM sol_ficha_examen s " +
            "INNER JOIN preparatorias_de_procedencia p " +
            "ON s.clave_preparatoria = p.clave_preparatoria " +
            "WHERE s.no_recibo > 0 " +
            "AND periodo = ?1 " +
            "GROUP BY p.clave_preparatoria " +
            "ORDER BY cantidadFichas DESC", nativeQuery = true)
    List<CantidadFichasPorPrepaDTO> buscarFichasPorCarrerasPreparatoria(Integer periodo);
}
