package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.Service.AspiranteService;
import com.sii.aspirantes.aspirantes.Service.EmailSenderService;
import com.sii.aspirantes.aspirantes.Service.ExcelService;
import com.sii.aspirantes.aspirantes.Service.NotificacionesService;
import com.sii.aspirantes.aspirantes.dto.aspirante.*;
import com.sii.aspirantes.aspirantes.dto.evaluacion.PeriodoEvaluacionDocenteDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/dda/solicitudes")
public class AspiranteAdminController {

    private final AspiranteService aspiranteService;
    private final ExcelService excelService;
    private final EmailSenderService emailSenderService;
    private final NotificacionesService notificacionesService;

    public AspiranteAdminController(AspiranteService aspiranteService, ExcelService excelService, EmailSenderService emailSenderService, NotificacionesService notificacionesService) {
        this.aspiranteService = aspiranteService;
        this.excelService = excelService;
        this.emailSenderService = emailSenderService;
        this.notificacionesService = notificacionesService;
    }

    @GetMapping("/{no_solicitud}")
    public DatosAspiranteDTO getAspirante(@PathVariable(value = "no_solicitud") Long noSolicitud){
        return aspiranteService.obtenerAspirantePorNoSolicitud(noSolicitud);
    }

    @GetMapping("/lista/{periodo}")
    public List<AspiranteDTO> getSolicitudesPorPeriodo(@PathVariable(value = "periodo") int periodo){
        return aspiranteService.getSolicitudesAspirantesPorPeriodo(periodo);
    }

    @GetMapping("/por_municipio")
    public List<AspiranteFichaDTO> getAspirantePorMunicipio(@RequestParam String periodo,
                                                            @RequestParam Integer estado,
                                                            @RequestParam String municipio){
        return aspiranteService.obtenerAspirantesPorMunicipio(periodo, estado, municipio);
    }

    @GetMapping("/estadistico/fichas_por_municipio/{periodo}")
    public List<ConteoFichasMunicipiosDTO> contarFichasAspirantesPorMunicipios(@PathVariable String periodo){
        return aspiranteService.contarFichasAspirantesPorMunicipios(periodo);
    }

    //Lista de FICHAS de aspirantes por carrera y aula
    @GetMapping("/fichas/por_carrera")
    public List<AspiranteFichaDTO> obtenerFichaAspirantesPorCarrera(@RequestParam Integer periodo,
                                                                    @RequestParam Integer carrera){
        return aspiranteService.obtenerFichaAspirantesPorCarreraAulaV2(periodo, carrera, null);
    }

    @GetMapping("/fichas/por_carrera/excel")
    public ResponseEntity<Resource> generarExcelDeFichasPorCarrera(@RequestParam String periodo,
                                                                   @RequestParam Integer reticula) throws IOException {
        String filename = "concentrado_fichas.xlsx";

        ByteArrayInputStream actualData = excelService.getActualDataByCarrera(periodo, reticula);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

    //Lista de FICHAS de aspirantes por carrera y aula TODAS LAS CARRERAS
    @GetMapping("/fichas/{periodo}/aula")
    public List<AspiranteFichaDTO> obtenerFichaAspirantesTodasCarreras(@PathVariable Integer periodo){
        //Falta considerar el aula
        return aspiranteService.obtenerFichaAspirantesTodasCarrerasAulas(periodo, null);
    }

    @GetMapping("/fichas/excel")
    public ResponseEntity<Resource> generarExcelDeFichas(@RequestParam String periodo) throws IOException {
        String filename = "concentrado_fichas.xlsx";

        ByteArrayInputStream actualData = excelService.getActualData(periodo);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

    //Estadística de fichas  de aspirantes por carrera
    @GetMapping("/fichas/carrera")
    List<AspirantesPorCarreraDTO> fichasAspirantesCarrera(){
        return aspiranteService.fichasAspirantesCarrera();
    }

    //Lista de fichas de aspirantes por carrera
    @GetMapping("/fichas/{periodo}/carrera/{carrera}")
    public List<AspiranteFichaCarerraDTO> obtenerFichasAspirantesPorCarrera(@PathVariable Integer periodo,
                                                                            @PathVariable Integer carrera){
        return aspiranteService.obtenerFichasAspirantesPorCarrera(periodo, carrera);
    }

    //Lista de fichas de aspirantes por carrera TODAS LAS CARRERAS
    @GetMapping("/fichas/{periodo}")
    public List<AspiranteFichaCarerraDTO> obtenerFichasAspirantesTodasCarreras(@PathVariable Integer periodo){
        return aspiranteService.obtenerFichasAspirantesTodasCarreras(periodo);
    }

    //Comparativo de solicitudes de aspirantes

    //Atención a la demanda (licenciatura) alumnos de periodo normal
    @GetMapping("/atencion-demanda/{periodo}")
    public List<IAtencionDemandaAlumnos> atencionDemandaLicenciatura(@PathVariable Integer periodo){
        return aspiranteService.atencionDemandaLicenciatura(periodo);
    }


    //Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera
    @GetMapping("/seleccionar/{periodo}")
    public List<SeleccionFichaAspiranteRequest> mostrarListaFichasAspirantes(@PathVariable Integer periodo){
        return aspiranteService.getDatosFichaAspirantes(periodo);
    }

    @GetMapping("/seleccionar/{periodo}/carrera/{carrera}")
    public List<SeleccionFichaAspiranteRequest> mostrarListaFichasAspirantesPorCarrera(@PathVariable Integer periodo,
                                                                             @PathVariable Integer carrera){
        return aspiranteService.getDatosFichaAspirantesPorCarrera(periodo, carrera);
    }

    @PutMapping("/seleccionar/{periodo}")
    //Modificar estatusExamen en: Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera TODAS LAS CARRERAS
    public List<SeleccionFichaAspiranteRequest> modificarListaFichasEstatusExamen(@PathVariable Integer periodo,
                                                                                  @RequestBody List<SeleccionFichaAspiranteRequest> listaFichas){
        return aspiranteService.modificarListaFichasEstatusExamen(periodo, null, listaFichas);
    }

    //Modificar estatusExamen en: Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera
    @PutMapping("/seleccionar/{periodo}/carrera/{reticula}")
    public List<SeleccionFichaAspiranteRequest> modificarListaFichasEstatusExamenPorCarrera(@PathVariable Integer periodo,
                                                                                            @PathVariable Integer reticula,
                                                                                            @RequestBody List<SeleccionFichaAspiranteRequest> listaFichas){
        return aspiranteService.modificarListaFichasEstatusExamen(periodo, reticula, listaFichas);

    }

    //CAMBIOS DE DATOS DE ASPIRANTES (TABLA)
    @GetMapping("/editar/{periodo}")
    public List<SeleccionFichaAspiranteRequest> mostrarDatosAspirantes(@PathVariable Integer periodo){
        return aspiranteService.getDatosFichaAspirantes(periodo);
    }

    @GetMapping("/editar/{periodo}/carrera/{carrera}")
    public List<SeleccionFichaAspiranteRequest> mostrarDatosAspirantesPorCarrera(@PathVariable Integer periodo,
                                                                                 @PathVariable Integer carrera){
        return aspiranteService.getDatosFichaAspirantesPorCarrera(periodo, carrera);
    }

    //CAMBIOS DE DATOS DE ASPIRANTES
    @PutMapping("/editar/{periodo}")
    public List<SeleccionFichaAspiranteRequest> cambiosDeDatosDeAspirantes(@PathVariable Integer periodo,
                                                                           @RequestBody List<SeleccionFichaAspiranteRequest> listaFichas){
        return aspiranteService.cambiosDeDatosDeAspirantes(periodo, null, listaFichas);
    }

    //CAMBIOS DE DATOS DE ASPIRANTES TODAS LAS CARRERAS
    @PutMapping("/editar/{periodo}/carrera/{reticula}")
    public List<SeleccionFichaAspiranteRequest> cambiosDeDatosDeAspirantes(@PathVariable Integer periodo,
                                                                           @PathVariable Integer reticula,
                                                                           @RequestBody List<SeleccionFichaAspiranteRequest> listaFichas){
        return aspiranteService.cambiosDeDatosDeAspirantes(periodo, reticula, listaFichas);
    }

    // Comparativo de solicitudes de aspirantes
    @GetMapping("/comparativoSolicitudes")
    public ResponseEntity<List<ComparativoSolicitudesDTO>> comparativoSolicitudesAspirante(@RequestParam Integer periodo) throws ParseException {
        return new ResponseEntity<>(aspiranteService.comparativoSolicitudesAspirante(20231), HttpStatus.OK);
    }


    //Mostrar lista de fecha de inicio / cierre de periodo de inscripción
    @GetMapping("/periodo_nuevo_ingreso")
    public ResponseEntity<List<PeriodoEscolar>> mostrarPeriodosNuevoIngreso(){
        return new ResponseEntity<>(aspiranteService.buscarPeriodosNuevoIngreso(), HttpStatus.OK);
    }

    //Mostrar fecha de inicio / cierre de periodo de inscripción
    @PostMapping("/periodo_nuevo_ingreso")
    public ResponseEntity<PeriodoEvaluacionDocenteDTO> registrarNuevoPeriodoInscripcion(@RequestBody PeriodoEvaluacionDocenteDTO periodoNuevoIngreso){
        return new ResponseEntity<>(aspiranteService.registrarNuevoPeriodoInscripcion(periodoNuevoIngreso), HttpStatus.OK);
    }

    //Mostrar fecha de inicio / cierre de periodo de inscripción
    @GetMapping("/periodo_nuevo_ingreso/{periodo}")
    public ResponseEntity<PeriodoEvaluacionDocenteDTO> mostrarPeriodoNuevoIngreso(@PathVariable String periodo){

        return new ResponseEntity<>(aspiranteService.mostrarPeriodoNuevoIngreso(periodo), HttpStatus.OK);
    }

    //Modificar fecha de inicio / cierre de periodo de inscripción
    @PutMapping("/periodo_nuevo_ingreso")
    public ResponseEntity<PeriodoEvaluacionDocenteDTO> actualizarPeriodoNuevoIngreso(@RequestBody PeriodoEvaluacionDocenteDTO periodo){

        return new ResponseEntity<>(aspiranteService.actualizrPeriodoNuevoIngreso(periodo), HttpStatus.OK);
    }

    //ESTADÍSTICO DE FICHAS POR ESCUELA DE PROCEDENCIA -
    @GetMapping("/estadistico/fichas_por_preparatoria")
    public ResponseEntity<List<FichaPorPreparatoriaDTO>> buscarFichasPorPreparatoriaProcedencia(@RequestParam String periodo){
        return new ResponseEntity<>(aspiranteService.buscarFichasPorPreparatoriaProcedencia(periodo), HttpStatus.OK);
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR ESTADO
    @GetMapping("/estadistico/fichas_por_preparatoria/por_estado")
    public ResponseEntity<List<FichaPorPreparatoriaDTO>> buscarFichasPorPreparatoriaProcedenciaEstado(@RequestParam String periodo,
                                                                                                      @RequestParam Integer estado){
        return new ResponseEntity<>(aspiranteService.buscarFichasPorPreparatoriaProcedenciaEstado(periodo, estado), HttpStatus.OK);
    }

    @GetMapping("/estadistico/fichas_por_preparatoria/por_estado/excel")
    public ResponseEntity<Resource> generarExcelDeFichasPorPreparatoriaProcedenciaEstado(@RequestParam String periodo,
                                                                                         @RequestParam Integer estado) throws IOException {
        String filename = "fichas_preparatorias.xlsx";

        ByteArrayInputStream actualData = excelService.fichasPorPreparatoriasProcedenciaEstado(periodo, estado);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR ESTADO -> MUNICIPIO
    @GetMapping("/estadistico/fichas_por_preparatoria/por_municipio")
    public List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedenciaMunicipio(@RequestParam String periodo,
                                                                                  @RequestParam Integer estado,
                                                                                  @RequestParam String municipio){
        return aspiranteService.buscarFichasPorPreparatoriaProcedenciaMunicipio(periodo, estado, municipio);
    }

    @GetMapping("/estadistico/fichas_por_preparatoria/por_municipio/excel")
    public ResponseEntity<Resource> generarExcelDeFichasPorPreparatoriaMunicipio(@RequestParam String periodo,
                                                                                   @RequestParam Integer estado,
                                                                                   @RequestParam String municipio) throws IOException {
        String filename = "fichas_preparatorias.xlsx";

        ByteArrayInputStream actualData = excelService.fichasPorPreparatoriasProcedenciaMunicipio(periodo, estado, municipio);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR ESTADO -> MUNICIPIO -> NOMBRE DE PREPARATORIA
    @GetMapping("/estadistico/fichas_por_preparatoria/por_nombre")
    public ResponseEntity<List<FichaPorPreparatoriaDTO>> buscarFichasPorNombrePreparatoriaProcedencia(@RequestParam String periodo,
                                                                               @RequestParam Integer estado,
                                                                               @RequestParam String municipio,
                                                                               @RequestParam String clavePreparatoria){
        return new ResponseEntity<>(aspiranteService.buscarFichasPorNombrePreparatoriaProcedencia(periodo, estado, municipio, clavePreparatoria), HttpStatus.OK);
    }

    @GetMapping("/estadistico/fichas_por_preparatoria/por_nombre/excel")
    public ResponseEntity<Resource> generarExcelDeFichasPorPreparatoriaProcedencia(@RequestParam String periodo,
                                                                                   @RequestParam Integer estado,
                                                                                   @RequestParam String municipio,
                                                                                   @RequestParam String clavePreparatoria) throws IOException {
        String filename = "fichas_preparatorias.xlsx";

        ByteArrayInputStream actualData = excelService.fichasPorNombrePreparatoriaProedencia(periodo, estado, municipio, clavePreparatoria);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

    @GetMapping("/estadistico/comparativo")
    public ResponseEntity<List<CantidadFichasPorPrepaDTO>> conteoDeFichasPorPreparatoriaProcedencia(@RequestParam String periodo){
        return new ResponseEntity<>(aspiranteService.buscarFichasPorCarrerasPreparatoria(periodo), HttpStatus.OK);
    }

    @GetMapping("/estadistico/comparativo/por_carrera/todas")
    public ResponseEntity<List<ConteoFichasPrepaDTO>> conteoFichasPorTodasCarreras(@RequestParam String periodo){
        return new ResponseEntity<>(aspiranteService.conteoFichasPorTodasCarreras(periodo), HttpStatus.OK);
    }

    @GetMapping("/estadistico/comparativo/por_carrera")
    public ResponseEntity<List<FichasCarrerasPrepaTotalDTO>> conteoFichasPorCarrera(@RequestParam String periodo){
        return new ResponseEntity<>(aspiranteService.conteoFichasPorCarrera(periodo), HttpStatus.OK);
    }

    @GetMapping("/estadistico/solicitudes")
    public ResponseEntity<SolicitudesPorMes> conteoSolicitudesPorMes(@RequestParam String periodo){
        return new ResponseEntity<>(aspiranteService.conteoSolicitudesPorMes(periodo), HttpStatus.OK);
    }

    @GetMapping("/estadistico/solicitudes/por_periodo")
    public ResponseEntity<SolicitudesPorMes> conteoSolicitudesYFichasPorPeriodo(){
        return new ResponseEntity<>(aspiranteService.contarSolicitudesPorPeriodos(), HttpStatus.OK);
    }

    @PostMapping("/notificaciones")
    public ResponseEntity<String> enviarNotificacionesAspirantes(@RequestParam String periodo,
                                                                 @RequestParam Integer opcion,
                                                                 @RequestParam Long idNotificacion){

        aspiranteService.enviarNotificacionesAspirantes(periodo, opcion, idNotificacion);
        String[] emailList = aspiranteService.obtenerEmailAspirantesPorPeriodo(periodo, opcion);

        String contenidoNotificacion = notificacionesService.buscarContenidoNotificacion(idNotificacion);
        String asunto = "NOTIFICACIÓN DEL INSTITUTO TECNOLÓGICO DE CUAUTLA";

        emailSenderService.sendSimpleEmail(
                emailList,
                contenidoNotificacion,
                asunto);

        return new ResponseEntity<>("Notificación enviada", HttpStatus.OK);
    }

    @PostMapping("/notificaciones/selectos")
    public ResponseEntity<String> enviarNotificacionAspirantesSeleccionados(@RequestParam Long idNotificacion,
                                                                            @RequestBody String aspirantes){
        aspiranteService.enviarNotificacionAspirantesSeleccionados(aspirantes, idNotificacion);
        //Obtener correo electrónico de los aspirantes seleccionados
        String[] emailList = aspiranteService.obtenerEmailAspirantesSelectos(aspirantes);
        //Busca el contenido de la notificación
        String contenidoNotificacion = notificacionesService.buscarContenidoNotificacion(idNotificacion);
        //Enviar email
        emailSenderService.sendSimpleEmail(emailList,
                contenidoNotificacion,
                "NOTIFICACIÓN DEL INSTITUTO TECNOLÓGICO DE CUAUTLA");
        return new ResponseEntity<>("Notificación enviada", HttpStatus.OK);
    }
}
