package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.Repository.*;
import com.sii.aspirantes.aspirantes.Utils.RegexValidators;
import com.sii.aspirantes.aspirantes.dto.ERole;
import com.sii.aspirantes.aspirantes.dto.aspirante.*;
import com.sii.aspirantes.aspirantes.dto.evaluacion.PeriodoEvaluacionDocenteDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class AspiranteService {

    private final AspiranteRepository aspiranteRepository;
    private final CarreraRepository carreraRepository;
    private final PeriodoEscolarRepository periodoEscolarRepository;
    private final RoleRepository roleRepository;
    private final PreparatoriasProcedenciaRepository preparatoriasProcedenciaRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final NotificacionesRepository notificacionesRepository;

    private static final int CANTIDAD_PERIODOS = 5;


    public DatosAspiranteDTO obtenerAspirantePorNoSolicitud(Long noSolicitud) {
        Aspirante aspirante = aspiranteRepository.findById(noSolicitud)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Aspirante " + noSolicitud + " no encontrado"));

        return mapearDatosAspirantes(aspirante);
    }

    public boolean validarCurpRegistrado(String curp) {
        //Valida que no esté registrado un aspirante con la misma CURP
        return aspiranteRepository.validarAspirantePorCurp(curp).isPresent();
    }

    public Aspirante guardarAspirante(DatosRegistroAspiranteDTO aspirante) throws Exception {

        if (validarCurpRegistrado(aspirante.getCurp())) {
            throw new Exception("CURP " + aspirante.getCurp() + " ya registrada");
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        String fecha = myDateObj.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String hora = myDateObj.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String periodoEscolar = periodoEscolarRepository.obtenerPeriodoEscolarActual();
        Roles rol = roleRepository.findByRol(ERole.ROLE_ASPIRANTE).orElse(null);

        //Validación de campos
        if(!RegexValidators.validateCURP(aspirante.getCurp())){
            throw new Exception("Formato de CURP no válido (" + aspirante.getCurp() + ")");
        }
        if(!RegexValidators.validateEmail(aspirante.getCorreoElectronico())){
            throw new Exception("Formato de email no válido (" + aspirante.getCorreoElectronico() + ")");
        }

        Aspirante aspiranteNuevo = Aspirante.builder()
                .fechaAtencion(fecha)
                .horaAtencion(hora)
                .fechaRegistro(new Date())
                .periodo(Integer.parseInt(periodoEscolar))
                .telefono("")
                .noRecibo(0L)
                .nombreAspirante(aspirante.getNombreAspirante())
                .apellidoPaterno(aspirante.getApellidoPaterno())
                .apellidoMaterno(aspirante.getApellidoMaterno())
                .nip(aspirante.getNip())
                .curp(aspirante.getCurp())
                .correoElectronico(aspirante.getCorreoElectronico())
                .rol(rol)
                .build();
        System.out.println("rol = " + rol);

        return aspiranteRepository.save(aspiranteNuevo);
    }

    public Aspirante registrarDatosGenerales(Long id, DatosAspiranteDTO aspirante) throws Exception {
        Aspirante aspiranteRegistrado = aspiranteRepository.findById(id).orElse(null);

        if (Objects.isNull(aspiranteRegistrado)) {
            return null;
        }

        if(!RegexValidators.validatePhoneNumber(aspirante.getTelefono())){
            throw new Exception("Forato de teléfono no válido (" + aspirante.getCurp() + ")");
        }

        aspiranteRegistrado = mapearRegistroDatosGenerales(aspiranteRegistrado, aspirante);

        return aspiranteRepository.save(aspiranteRegistrado);
    }

    //Lista de solicitudes de aspirantes registrados - Lista de solicitudes de aspirantes
    public List<AspiranteDTO> getSolicitudesAspirantesPorPeriodo(Integer periodo) {
        return aspiranteRepository.getSolicitudesAspirantesPorPeriodo(periodo);
    }

    //Lista de fichas de aspirantes registrados - Lista de fichas de aspirantes POR CARRERA
    public List<AspiranteFichaDTO> obtenerFichaAspirantesPorCarreraAulaV2(Integer periodo, Integer carrera, String aula) {
        return aspiranteRepository.obtenerFichaAspirantesPorCarreraAulaV2(periodo, carrera);
    }

    //Lista de fichas de aspirantes registrados - Lista de fichas de aspirantes TODAS LAS CARRERAS
    public List<AspiranteFichaDTO> obtenerFichaAspirantesTodasCarrerasAulas(Integer periodo, String aula) {
        return aspiranteRepository.obtenerFichaAspirantesTodasCarrerasAula(periodo);
    }

    //Lista de fichas de aspirantes por carrera
    public List<AspiranteFichaCarerraDTO> obtenerFichasAspirantesPorCarrera(Integer periodo, Integer carrera) {
        return aspiranteRepository.obtenerFichaAspirantePorCarrera(periodo, carrera);
    }

    //Lista de fichas de aspirantes por carrera TODAS LAS CARRERAS
    public List<AspiranteFichaCarerraDTO> obtenerFichasAspirantesTodasCarreras(Integer periodo) {
        return aspiranteRepository.obtenerFichaAspiranteTodasCarreras(periodo);
    }

    //Estadística de fichas de aspirantes por carrera
    public List<AspirantesPorCarreraDTO> fichasAspirantesCarrera() {
        return aspiranteRepository.fichasAspirantesCarrera();
    }

    public List<IAtencionDemandaAlumnos> atencionDemandaLicenciatura(Integer periodo) {
        return aspiranteRepository.getListaAtencionDemandaLicenciatura(periodo);
    }

    //Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera
    public List<SeleccionFichaAspiranteRequest> getDatosFichaAspirantesPorCarrera(Integer periodo, Integer reticula) {
        return aspiranteRepository.getDatosFichaAspirantesPorCarrera(periodo, reticula);
    }

    //Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera  TODAS LAS CARRERAS
    public List<SeleccionFichaAspiranteRequest> getDatosFichaAspirantes(Integer periodo) {
        return aspiranteRepository.getDatosFichaAspirantes(periodo);
    }

    //Modificar estatusExamen en: Lista de fichas de aspirantes para seleccionar - Lista de fichas de aspirantes por carrera
    public List<SeleccionFichaAspiranteRequest> modificarListaFichasEstatusExamen(Integer periodo,
                                                                                  Integer reticula,
                                                                                  List<SeleccionFichaAspiranteRequest> listaFichas) {
        //En este punto, se debió haber activado la ficha al aspirante
        List<Aspirante> listaAspirantes = new ArrayList<>();
        Aspirante aspirante;

        for (SeleccionFichaAspiranteRequest ficha : listaFichas) {
            aspirante = aspiranteRepository.findById(ficha.getNoSolicitud()).orElseThrow(() ->
                    new UsernameNotFoundException("Ficha No. " + ficha.getNoFicha() + " no registrada"));

            aspirante.setAceptado(ficha.isAceptado());
            listaAspirantes.add(aspirante);
        }

        aspiranteRepository.saveAll(listaAspirantes);

        if (Objects.isNull(reticula)) {
            return getDatosFichaAspirantes(periodo);
        }

        return getDatosFichaAspirantesPorCarrera(periodo, reticula);
    }

    //CAMBIOS DE DATOS DE ASPIRANTES
    public List<SeleccionFichaAspiranteRequest> cambiosDeDatosDeAspirantes(Integer periodo,
                                                                           Integer reticula,
                                                                           List<SeleccionFichaAspiranteRequest> listaFichas) {
        List<Aspirante> listaAspirantes = new ArrayList<>();
        Aspirante aspirante;
        Carrera carrera;
        for (SeleccionFichaAspiranteRequest ficha : listaFichas) {
            aspirante = aspiranteRepository.findById(ficha.getNoSolicitud()).orElseThrow(() ->
                    new UsernameNotFoundException("Ficha No. " + ficha.getNoFicha() + " no registrada"));

            aspirante.setNoSolicitud(ficha.getNoSolicitud());
            aspirante.setNoRecibo(ficha.getNoFicha());
            aspirante.setNombreAspirante(ficha.getNombreAspirante());
            aspirante.setApellidoPaterno(ficha.getApellidoPaterno());
            aspirante.setApellidoMaterno(ficha.getApellidoMaterno());

            carrera = carreraRepository.findById(ficha.getReticula()).orElse(null);
            aspirante.setCarreraOp1(carrera);
            aspirante.setAceptado(ficha.isAceptado());
            listaAspirantes.add(aspirante);
        }

        aspiranteRepository.saveAll(listaAspirantes);

        if (Objects.isNull(reticula)) {
            return getDatosFichaAspirantes(periodo);
        }

        return getDatosFichaAspirantesPorCarrera(periodo, reticula);
    }

    /*
    Método para hacer el comparativo de fichas entre un rango de fechas determinado
     */

    public List<ComparativoSolicitudesDTO> comparativoSolicitudesAspirante(Integer periodo) throws ParseException {
        List<ComparativoSolicitudesDTO> listaComparativoSolicitudes = new ArrayList<>();
        PeriodoEscolar periodoNuevoIngreso = periodoEscolarRepository.findById(periodo.toString())
                .orElseThrow(() ->
                        new EntityNotFoundException("Periodo no encontrado"));

        ComparativoSolicitudesDTO dato;
        TimeZone timeZone = TimeZone.getTimeZone("GMT-6");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
        //Fechas
        Date fechaInicio = periodoNuevoIngreso.getInicioInscripcion();
        Calendar fechaCierrePeriodo = new GregorianCalendar();
        fechaCierrePeriodo.setTime(periodoNuevoIngreso.getFinInscripcion());
        Date fechaTermino = fechaInicio;

        //Objeto Calendar

        formato.setTimeZone(timeZone);
        formato2.setTimeZone(timeZone);
        Calendar fechaLimite = Calendar.getInstance(); //Representa la fecha actual
        fechaLimite.setTimeZone(timeZone);

        Calendar calendario = new GregorianCalendar(); //FechaFinal
        calendario.setTime(fechaInicio);


        int noFichas = 0;
        int acumulado = 0;

        while ((calendario.before(fechaLimite) && calendario.before(fechaCierrePeriodo))) {
            //Mientras la fecha sea menor a la fecha actual y a la fecha de cierre del periodo
            StringBuilder rangoFechas = new StringBuilder();

            calendario.add(Calendar.DATE, 7);
            fechaTermino = calendario.getTime();

            noFichas = aspiranteRepository.contarSolicitudesPorFecha(fechaInicio, fechaTermino, periodo);
            acumulado += noFichas;
            rangoFechas.append(formato.format(fechaInicio));
            rangoFechas.append(" - ");
            rangoFechas.append(formato.format(fechaTermino));

            dato = ComparativoSolicitudesDTO.builder()
                    .numero(noFichas)
                    .subtotal(acumulado)
                    .rangoFechas(rangoFechas.toString())
                    .build();

            calendario.setTime(fechaTermino); //Incluye el incremento
            fechaInicio = calendario.getTime();
            listaComparativoSolicitudes.add(dato);
        }

        return listaComparativoSolicitudes;
    }

    //Mostrar lista de fecha de inicio / cierre de periodo de inscripción
    public List<PeriodoEscolar> buscarPeriodosNuevoIngreso() {
        return periodoEscolarRepository.findAll();
    }

    //Registrar fecha de inicio / cierre de periodo de inscripción
    public PeriodoEvaluacionDocenteDTO registrarNuevoPeriodoInscripcion(PeriodoEvaluacionDocenteDTO periodoNuevoIngreso) {
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodoNuevoIngreso.getPeriodo()).orElse(null);
        periodoEscolar.setInicioInscripcion(periodoNuevoIngreso.getFechaInicial());
        periodoEscolar.setFinInscripcion(periodoNuevoIngreso.getFechaTermino());

        periodoEscolarRepository.save(periodoEscolar);

        return periodoNuevoIngreso;
    }

    //Mostrar fecha de inicio / cierre de periodo de inscripción
    public PeriodoEvaluacionDocenteDTO mostrarPeriodoNuevoIngreso(String periodo) {
        PeriodoEscolar periodoNuevoIngreso = periodoEscolarRepository.findById(periodo)
                .orElseThrow(() ->
                        new EntityNotFoundException("Periodo no encontrado"));



        return new PeriodoEvaluacionDocenteDTO(periodo, periodoNuevoIngreso.getInicioInscripcion(), periodoNuevoIngreso.getFinInscripcion());
    }

    //Modificar fecha de inicio / cierre de periodo de inscripción
    public PeriodoEvaluacionDocenteDTO actualizrPeriodoNuevoIngreso(PeriodoEvaluacionDocenteDTO periodo) {
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodo.getPeriodo())
                .orElseThrow(() ->
                        new EntityNotFoundException("Periodo no encontrado"));

        periodoEscolar.setInicioInscripcion(periodo.getFechaInicial());
        periodoEscolar.setFinInscripcion(periodo.getFechaInicial());
        periodoEscolarRepository.save(periodoEscolar);
        return periodo;
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA
    public List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedencia(String periodo) {
        return aspiranteRepository.buscarFichasPorPreparatoriaProcedencia(Integer.parseInt(periodo));
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR ESTADO
    public List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedenciaEstado(String periodo, Integer estado) {
        return aspiranteRepository.buscarFichasPorPreparatoriaProcedenciaEstado(Integer.parseInt(periodo), estado);
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR MUNICIPIO
    public List<FichaPorPreparatoriaDTO> buscarFichasPorPreparatoriaProcedenciaMunicipio(String periodo,
                                                                                         Integer estado,
                                                                                         String municipio) {
        return aspiranteRepository.buscarFichasPorPreparatoriaProcedenciaMunicipio(Integer.parseInt(periodo), estado, municipio);
    }

    //LISTA DE FICHAS POR ESCUELA DE PROCEDENCIA - POR NOMBRE DE PREPARATORIA
    public List<FichaPorPreparatoriaDTO> buscarFichasPorNombrePreparatoriaProcedencia(String periodo,
                                                                                      Integer estado,
                                                                                      String municipio,
                                                                                      String clavePreparatoria) {
        return aspiranteRepository.buscarFichasPorNombrePreparatoriaProcedencia(Integer.parseInt(periodo), estado, municipio, clavePreparatoria);
    }

    //CONTEO DE FICHAS POR CARRERAS Y PREPARATORIA ESPECÍFICA
    public List<CantidadFichasPorPrepaDTO> buscarFichasPorCarrerasPreparatoria(String periodo) {
        return aspiranteRepository.buscarFichasPorCarrerasPreparatoria(Integer.parseInt(periodo));
    }

    public List<ConteoFichasPrepaDTO> conteoFichasPorTodasCarreras(String periodo) {
        return aspiranteRepository.conteoFichasPorTodasCarreras(Integer.parseInt(periodo));
    }

    public List<FichasCarrerasPrepaTotalDTO> conteoFichasPorCarrera(String periodo) {
        List<FichasCarrerasPrepaTotalDTO> fichasCarrerasPrepaTotalDTOS = new ArrayList<>();
        FichasCarrerasPrepaTotalDTO fichasCarrerasPrepaTotalDTO;
        List<Carrera> listaCarreras = carreraRepository.findAll();
        List<ConteoFichasPrepaDTO> conteoFichasPrepaDTOS;

        for (Carrera carrera : listaCarreras) {
            conteoFichasPrepaDTOS = aspiranteRepository.conteoFichasPorCarrera(Integer.parseInt(periodo), carrera.getReticula());

            fichasCarrerasPrepaTotalDTO = FichasCarrerasPrepaTotalDTO.builder()
                    .nombreCarrera(carrera.getNombreCarrera())
                    .listaPreparatorias(conteoFichasPrepaDTOS)
                    .build();

            fichasCarrerasPrepaTotalDTOS.add(fichasCarrerasPrepaTotalDTO);
        }

        return fichasCarrerasPrepaTotalDTOS;
    }

    public SolicitudesPorMes conteoSolicitudesPorMes(String periodo) {
        return this.contarSolicitudes(Integer.parseInt(periodo));
    }

    public SolicitudesPorMes contarSolicitudesPorPeriodos() {
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);
        return this.contarSolicitudesYFichasPorPeriodo(Integer.parseInt(periodoEscolar.getPeriodo()));
    }


    public List<AspiranteFichaDTO> obtenerAspirantesPorMunicipio(String periodo, Integer estado, String municipio) {
        return aspiranteRepository.obtenerFichasAspirantesPorMunicipio(Integer.parseInt(periodo), estado, municipio);
    }

    public List<ConteoFichasMunicipiosDTO> contarFichasAspirantesPorMunicipios(String periodo) {
        return aspiranteRepository.contarFichasAspirantesPorMunicipio(Integer.parseInt(periodo));
    }


    //ENVÍO DE NOTIFICACIONES
    public void enviarNotificacionesAspirantes(String periodo, Integer opcion, Long idNotificacion) {
        List<Aspirante> listaAspirantes;
        Set<Notificaciones> notificacionesNuevas;
        Set<Notificaciones> notificaciones;

        switch (opcion) {
            case 1: { //Todos los aspirantes
                listaAspirantes = aspiranteRepository.obtenerAspirantePorPeriodo(Integer.parseInt(periodo));
                break;
            }
            case 2: { //Todos los aspirantes del periodo actual con ficha generada
                listaAspirantes = aspiranteRepository.obtenerFichaAspirantesPorPeriodo(Integer.parseInt(periodo));
                break;
            }
            case 3: { //Todos los aspirantes con estatus de pago pendiente
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusPago(Integer.parseInt(periodo), "PENDIENTE");
                break;
            }
            case 4: { //Todos los aspirantes con estatus de pago aceptado
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusPago(Integer.parseInt(periodo), "ACEPTADO");
                break;
            }
            case 5: { //Todos los aspirantes aceptados
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusAceptado(Integer.parseInt(periodo), true);
                break;
            }
            case 6: { //Todos los aspirantes no aceptados
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusAceptado(Integer.parseInt(periodo), false);
                break;
            }
            case 7: { //Todos los aspitantes que no han presentado examen
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusExamen(Integer.parseInt(periodo), "NO PRESENTADO");
                break;
            }
            case 8: { //Todos los aspitantes que presentaron examen
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusExamen(Integer.parseInt(periodo), "PRESENTADO");
                break;
            }
            case 9: { //Todos los aspirantes que aprobaron el examen
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusExamen(Integer.parseInt(periodo), "APROBADO");
                break;
            }
            case 10: { //Todos los aspirantes que no aprobaron el examen
                listaAspirantes = aspiranteRepository.obtenerAspirantesPorEstatusExamen(Integer.parseInt(periodo), "NO APROBADO");
                break;
            }
            default: {
                listaAspirantes = aspiranteRepository.obtenerAspirantePorPeriodo(Integer.parseInt(periodo));
            }
        } //fin switch

        notificacionesNuevas = agregarNotificaciones(idNotificacion);

        for (Aspirante a : listaAspirantes) {
            notificaciones = a.getNotificaciones();
            Set<Notificaciones> finalNotificaciones = notificaciones;
            notificacionesNuevas.forEach(notificacion -> finalNotificaciones.add(notificacion));
            a.setNotificaciones(notificaciones);
        }

        aspiranteRepository.saveAll(listaAspirantes);
    }
    //MANDAR ESTOS MÉTODOS AL FINAL
    public String[] obtenerEmailAspirantesPorPeriodo(String periodo, Integer opcion){
        String[] emailList;

        switch (opcion) {
            case 1: { //Todos los aspirantes
                emailList = aspiranteRepository.obtenerCorreoAspirantes(Integer.parseInt(periodo)).toArray(new String[0]);
                break;
            }
            case 2: { //Todos los aspirantes del periodo actual con ficha generada
                emailList = aspiranteRepository.obtenerCorreoAspiranteFichas(Integer.parseInt(periodo)).toArray(new String[0]);
                break;
            }
            case 3: { //Todos los aspirantes con estatus de pago pendiente
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusPago(Integer.parseInt(periodo), "PENDIENTE").toArray(new String[0]);
                break;
            }
            case 4: { //Todos los aspirantes con estatus de pago aceptado
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusPago(Integer.parseInt(periodo), "ACEPTADO").toArray(new String[0]);
                break;
            }
            case 5: { //Todos los aspirantes aceptados
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusAceptado(Integer.parseInt(periodo), true).toArray(new String[0]);
                break;
            }
            case 6: { //Todos los aspirantes no aceptados
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusAceptado(Integer.parseInt(periodo), false).toArray(new String[0]);
                break;
            }
            case 7: { //Todos los aspitantes que no han presentado examen
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusExamen(Integer.parseInt(periodo), "NO PRESENTADO").toArray(new String[0]);
                break;
            }
            case 8: { //Todos los aspitantes que presentaron examen
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusExamen(Integer.parseInt(periodo), "PRESENTADO").toArray(new String[0]);
                break;
            }
            case 9: { //Todos los aspirantes que aprobaron el examen
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusExamen(Integer.parseInt(periodo), "APROBADO").toArray(new String[0]);
                break;
            }
            case 10: { //Todos los aspirantes que no aprobaron el examen
                emailList = aspiranteRepository.obtenerCorreoAspirantesPorEstatusExamen(Integer.parseInt(periodo), "NO APROBADO").toArray(new String[0]);
                break;
            }
            default: {
                emailList = aspiranteRepository.obtenerCorreoAspirantes(Integer.parseInt(periodo)).toArray(new String[0]);
            }
        }

        return emailList;
    }

    public void enviarNotificacionAspirantesSeleccionados(String aspirantes, Long idNotificacion) {
        String[] listaNoSolicitud = aspirantes.split(",");
        List<Aspirante> listaAspirantes = new ArrayList<>();
        Set<Notificaciones> notificacionesNuevas;
        Set<Notificaciones> notificaciones;
        Aspirante aspirante;

        for (String noSolicitud : listaNoSolicitud) {
            aspirante = aspiranteRepository.findById(Long.parseLong(noSolicitud)).orElse(null);
            listaAspirantes.add(aspirante);
        }

        notificacionesNuevas = agregarNotificaciones(idNotificacion);

        for (Aspirante a : listaAspirantes) {
            notificaciones = a.getNotificaciones();
            Set<Notificaciones> finalNotificaciones = notificaciones;
            notificacionesNuevas.forEach(notificacion -> finalNotificaciones.add(notificacion));
            a.setNotificaciones(notificaciones);
        }

        aspiranteRepository.saveAll(listaAspirantes);
    }

    public String[] obtenerEmailAspirantesSelectos(String aspirantes){
        String[] solicitudes = aspirantes.split(",");
        String[] emailList = new String[solicitudes.length];
        int index = 0;

        for (String s : solicitudes) {
            emailList[index] = aspiranteRepository.obtenerCorreoAspirantePorNoSolicitud(Long.parseLong(s));
            index++;
        }

        return emailList;
    }

    //Visualización de notificaciones por parte del aspirante
    public List<NotificacionDTO> notificacionesDelAspirante(Long noSolicitud) {
        List<NotificacionDTO> notificacionDTOS = new ArrayList<>();
        Set<Notificaciones> notificaciones = new HashSet<>();

        Aspirante aspirante = aspiranteRepository.findById(noSolicitud).orElse(null);
        notificaciones = aspirante.getNotificaciones();

        for (Notificaciones notificacion : notificaciones) {
            notificacionDTOS.add(new NotificacionDTO(notificacion.getAsunto(), notificacion.getContenido()));
        }

        return notificacionDTOS;
    }


    public List<EntidadFederativa> obtenerEntidadFederativa() {
        return aspiranteRepository.mostrarEntidadFederativa();
    }

    public List<Carrera> obtenerCarreras() {
        return aspiranteRepository.mostrarCarreras();
    }

    public SolicitudesPorMes contarSolicitudes(Integer periodo) {
        String anio = periodo.toString().substring(0, 4);
        String[] enero = {anio.concat("-01-01"), anio.concat("-02-01")};
        String[] febrero = {anio.concat("-02-01"), anio.concat("-03-01")};
        String[] marzo = {anio.concat("-03-01"), anio.concat("-04-01")};
        String[] abril = {anio.concat("-04-01"), anio.concat("-05-01")};
        String[] mayo = {anio.concat("-05-01"), anio.concat("-06-01")};
        String[] junio = {anio.concat("-06-01"), anio.concat("-07-01")};
        String[] julio = {anio.concat("-07-01"), anio.concat("-08-01")};
        SolicitudesPorMes listaSolicitudes = new SolicitudesPorMes();

        List<String[]> fechas = new ArrayList<>();
        fechas.add(enero);
        fechas.add(febrero);
        fechas.add(marzo);
        fechas.add(abril);
        fechas.add(mayo);
        fechas.add(junio);

        List<Long> solicitudesPorMes = new ArrayList<>();
        List<Long> fichasPorMes = new ArrayList<>();

        fechas.forEach(fecha -> {
            String fecha1 = fecha[0];
            String fecha2 = fecha[1];
            solicitudesPorMes.add(aspiranteRepository.contarSolcitudesPorMes(periodo, fecha1, fecha2));
            fichasPorMes.add(aspiranteRepository.contarFichasPorMes(periodo, fecha1, fecha2));
        });

        listaSolicitudes.setNoSolicitudes(solicitudesPorMes);
        listaSolicitudes.setNoFichas(fichasPorMes);

        return listaSolicitudes;
    }

    public SolicitudesPorMes contarSolicitudesYFichasPorPeriodo(Integer periodoActual) {
        SolicitudesPorMes solicitudesPorPeriodo = new SolicitudesPorMes();
        List<Long> listaSolicitudesPorPeriodo = new ArrayList<>();
        List<Long> listaFichasPorPeriodo = new ArrayList<>();
        List<Integer> periodos = new ArrayList<>();
        int resta = 10;
        int cantidadPeriodos = 5;
        periodos.add(periodoActual);

        //Mostrar fichas de 5 años antes que el actual
        do{
            periodos.add(periodoActual - resta);
            resta += 10;
            cantidadPeriodos--;
        } while(cantidadPeriodos > 0);

        periodos.forEach(periodo -> {
            listaSolicitudesPorPeriodo.add(aspiranteRepository.contarSolicitudesPorPeriodo(periodo));
            listaFichasPorPeriodo.add(aspiranteRepository.contarFichasPorPeriodo(periodo));
        });

        solicitudesPorPeriodo.setNoSolicitudes(listaSolicitudesPorPeriodo);
        solicitudesPorPeriodo.setNoFichas(listaFichasPorPeriodo);

        return solicitudesPorPeriodo;
    }

    public Set<Notificaciones> agregarNotificaciones(Long idNotificacion) {
        Notificaciones notificacion = notificacionesRepository.findById(idNotificacion).orElse(null);
        Set<Notificaciones> notificaciones = new HashSet<>();
        notificaciones.add(notificacion);
        return notificaciones;
    }

    public Aspirante mapearRegistroDatosGenerales(Aspirante aspiranteRegistrado, DatosAspiranteDTO aspirante){
        Carrera carrera1 = carreraRepository.findById(aspirante.getCarreraOp1()).orElse(null);
        Carrera carrera2 = carreraRepository.findById(aspirante.getCarreraOp2()).orElse(null);
        PreparatoriasProcedencia preparatoria = preparatoriasProcedenciaRepository.findById(aspirante.getClavePreparatoria()).orElse(null);
        EntidadFederativa entidadFederativa = entidadFederativaRepository.findById(aspirante.getEntidadFederativa()).orElse(null);

        aspiranteRegistrado.setFechaNacimiento(aspirante.getFechaNacimiento());
        aspiranteRegistrado.setEstadoCivil(aspirante.getEstadoCivil());
        aspiranteRegistrado.setGenero(aspirante.getGenero());
        aspiranteRegistrado.setNacionalidad(aspirante.getNacionalidad());
        aspiranteRegistrado.setCarreraOp1(carrera1);
        aspiranteRegistrado.setCarreraOp2(carrera2);
        aspiranteRegistrado.setClavePreparatoria(preparatoria);
        aspiranteRegistrado.setAnioEgreso(aspirante.getAnioEgreso());
        aspiranteRegistrado.setPromedioGeneral(aspirante.getPromedioGeneral());
        aspiranteRegistrado.setCalleNo(aspirante.getCalleNo());
        aspiranteRegistrado.setEntidadFederativa(entidadFederativa);
        aspiranteRegistrado.setMunicipio(aspirante.getMunicipio());
        aspiranteRegistrado.setCodigoPostal(aspirante.getCodigoPostal());
        aspiranteRegistrado.setColoniaAspirante(aspirante.getColoniaAspirante());
        aspiranteRegistrado.setTelefono(aspirante.getTelefono());
        aspiranteRegistrado.setCapacidadDiferente(aspirante.getCapacidadDiferente());
        aspiranteRegistrado.setTieneBeca(aspirante.getTieneBeca());
        aspiranteRegistrado.setQuienOtorgo(aspirante.getQuienOtorgo());
        aspiranteRegistrado.setZonaProcedencia(aspirante.getZonaProcedencia());
        aspiranteRegistrado.setEspecifiqueZonaProcedencia(aspirante.getEspecifiqueZonaProcedencia());
        aspiranteRegistrado.setProgramaGubernamental(aspirante.getProgramaGubernamental());
        aspiranteRegistrado.setApellidoPaternoPadre(aspirante.getApellidoPaternoPadre());
        aspiranteRegistrado.setApellidoMaternoPadre(aspirante.getApellidoMaternoPadre());
        aspiranteRegistrado.setNombrePadreAspirante(aspirante.getNombrePadreAspirante());
        aspiranteRegistrado.setVivePadre(aspirante.getVivePadre());
        aspiranteRegistrado.setApellidoPaternoMadre(aspirante.getApellidoPaternoMadre());
        aspiranteRegistrado.setApellidoMaternoMadre(aspirante.getApellidoMaternoMadre());
        aspiranteRegistrado.setNombreMadreAspirante(aspirante.getNombreMadreAspirante());
        aspiranteRegistrado.setViveMadre(aspirante.getViveMadre());
        aspiranteRegistrado.setEspecifiqueExtrangero(aspirante.getEspecifiqueExtrangero());

        return aspiranteRegistrado;
    }

    public DatosAspiranteDTO mapearDatosAspirantes(Aspirante aspirante){
        DatosAspiranteDTO datosAspiranteDTO = DatosAspiranteDTO.builder()
                .noSolicitud(aspirante.getNoSolicitud())
                .nombreAspirante(aspirante.getNombreAspirante())
                .apellidoPaterno(aspirante.getApellidoPaterno())
                .apellidoMaterno(aspirante.getApellidoMaterno())
                .nip(aspirante.getNip())
                .fechaNacimiento(aspirante.getFechaNacimiento())
                .estadoCivil(aspirante.getEstadoCivil())
                .genero(aspirante.getGenero())
                .nacionalidad(aspirante.getNacionalidad())
                .curp(aspirante.getCurp())
                .carreraOp1(aspirante.getCarreraOp1().getReticula())
                .carreraOp2(aspirante.getCarreraOp2().getReticula())
                .clavePreparatoria(aspirante.getClavePreparatoria().getClavePreparatoria())
                .anioEgreso(aspirante.getAnioEgreso())
                .promedioGeneral(aspirante.getPromedioGeneral())
                .calleNo(aspirante.getCalleNo())
                .entidadFederativa(aspirante.getEntidadFederativa().getEntidadFederativa())
                .municipio(aspirante.getMunicipio())
                .codigoPostal(aspirante.getCodigoPostal())
                .coloniaAspirante(aspirante.getColoniaAspirante())
                .correoElectronico(aspirante.getCorreoElectronico())
                .telefono(aspirante.getTelefono())
                .capacidadDiferente(aspirante.getCapacidadDiferente())
                .tieneBeca(aspirante.getTieneBeca())
                .quienOtorgo(aspirante.getQuienOtorgo())
                .zonaProcedencia(aspirante.getZonaProcedencia())
                .especifiqueZonaProcedencia(aspirante.getEspecifiqueZonaProcedencia())
                .programaGubernamental(aspirante.getProgramaGubernamental())
                .apellidoPaternoPadre(aspirante.getApellidoPaternoPadre())
                .apellidoMaternoPadre(aspirante.getApellidoMaternoPadre())
                .nombrePadreAspirante(aspirante.getNombrePadreAspirante())
                .vivePadre(aspirante.getVivePadre())
                .apellidoPaternoMadre(aspirante.getApellidoPaternoMadre())
                .apellidoMaternoMadre(aspirante.getApellidoMaternoMadre())
                .nombreMadreAspirante(aspirante.getNombreMadreAspirante())
                .viveMadre(aspirante.getViveMadre())
                .especifiqueExtrangero(aspirante.getEspecifiqueExtrangero())
                .fechaRegistro(aspirante.getFechaRegistro())
                .build();

        return datosAspiranteDTO;
    }

}
