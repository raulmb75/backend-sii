package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.Repository.*;
import com.sii.aspirantes.aspirantes.dto.evaluacion.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EvaluacionAluService {

    private final EvaluacionAlumnosRepository evaluacionAlumnosRepository;
    private final GruposRepository gruposRepository;
    private final AspectosRepository aspectosRepository;
    private final PersonalRepository personalRepository;
    private final PeriodoEscolarRepository periodoEscolarRepository;
    private final PreguntasRepository preguntasRepository;
    private final MateriasNoEvaluadasRepository materiasNoEvaluadasRepository;

    private static final int[][] RANGOS = {
            {0,9}, //0 0 - 0 1
            {10,21}, //1 0 - 1 1
            {21,32}, //2 0 - 2 1
            {32,42}, //3 0 - 3 1
            {42,53}, //4 0 - 4 1
            {53,62}, //5 0 - 5 1
            {62,65}, //6 0 - 6 1
            {65,69}, //7 0 - 7 1
            {69,72}, //8 0 - 8 1
            {72,75} //9 0 - 9 1
    };


    public EvaluacionAluService(EvaluacionAlumnosRepository evaluacionAlumnosRepository,
                                GruposRepository gruposRepository,
                                AspectosRepository aspectosRepository,
                                PersonalRepository personalRepository,
                                PeriodoEscolarRepository periodoEscolarRepository, PreguntasRepository preguntasRepository,  MateriasNoEvaluadasRepository materiasNoEvaluadasRepository) {
        this.evaluacionAlumnosRepository = evaluacionAlumnosRepository;
        this.gruposRepository = gruposRepository;
        this.aspectosRepository = aspectosRepository;
        this.personalRepository = personalRepository;
        this.periodoEscolarRepository = periodoEscolarRepository;
        this.preguntasRepository = preguntasRepository;
        this.materiasNoEvaluadasRepository = materiasNoEvaluadasRepository;
    }

    public List<EvaluacionAlumnos> mostrarEvaluacion() {
        return evaluacionAlumnosRepository.findAll();
    }

    public List<EvaluacionAlumnos> mostrarEvaluacionPorPeriodo(String periodo) {
        return evaluacionAlumnosRepository.buscarPorPeriodo(periodo);
    }

    public List<EvaluacionAlumnos> mostrarEvaluacionPorDocente(String docente) {
        return evaluacionAlumnosRepository.buscarPorDocente(docente);
    }

    public List<EvaluacionAlumnos> mostrarEvaluacionPorDepartamento(String claveArea) {
        return evaluacionAlumnosRepository.buscarPorDepartamento(claveArea);
    }

    public List<EvaluacionAlumnos> mostrarEvaluacionPorMateria(String materia) {
        return evaluacionAlumnosRepository.buscarPorClaveMateria(materia);
    }

    public EvaluacionAlumnos guardarEvaluacionIndividual(EvaluacionRequest evaluacionInd){

        EvaluacionAlumnos evaluacion = this.procesarObjetoEvaluacion(evaluacionInd);

        return evaluacionAlumnosRepository.save(evaluacion);
    }

    public List<EvaluacionAlumnos> guardarEvaluacionCompleta(List<EvaluacionRequest> listaEvaluacion){
        List<EvaluacionAlumnos> listaEvaluacionAlumnos =
                listaEvaluacion.stream().map(this::procesarObjetoEvaluacion).collect(Collectors.toList());

        return evaluacionAlumnosRepository.saveAll(listaEvaluacionAlumnos);
    }

    public List<DocentesEvaluacionAlumnos> obtenerDocentesEvaluacionAlumnosPorDepartamento(String periodo, String claveArea) {
        return evaluacionAlumnosRepository.buscarDocenteEvaluadosPorArea(periodo, claveArea);
    }

    public List<DocentesEvaluacionAlumnos> obtenerDocentesEvaluacionAlumnos(String periodo) {
        return evaluacionAlumnosRepository.buscarDocenteEvaluados(periodo);
    }


    /*
    Método para generar y mostrar los resultados de las evaluaciones de alumnos, mostradas por docente
     */
    public List<IEvaluacionPorDocente> mostrarDatosTablaEvaluacionPorDocente(String rfc, String periodo){
        //El periodo de visualización de resultados debe estar habilitado
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodo).orElse(null);

        if(!periodoActivo(periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes())){
            throw new RuntimeException("Periodo no habilitado");
        }

        return evaluacionAlumnosRepository.datosEvaluacionDocentePorDocente(periodo, rfc);
    }

    public List<EvaluacionTablaDocenteDTO> procesarResultadosEvaluacionPorDocente(String rfc) {
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);

        if(!periodoActivo(periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes())){
            throw new RuntimeException("Periodo no habilitado");
        }
        // Saber cuántas materias tiene un docente
        List<Grupos> gruposDocente = gruposRepository.buscarGruposPorDocente(rfc);
        float[] listaPromedioAspectos = new float[10];
        List<Aspectos> listaAspectos  = aspectosRepository.findAll();
        int alumnosEvaluaron = 0;
        List<EvaluacionAlumnos> evalAlumno;
        MateriasNoEvaluadas materiaNoEvaluada;
        //Por cada grupo se desplegará una tabla
        EvaluacionTablaDocenteDTO evaluacionTablaDocenteDTO;
        List<EvaluacionTablaDocenteDTO> listaEvaluacionTablaDocente = new ArrayList<>();
        for (Grupos grupo : gruposDocente) {
            //Hacer búsqueda de evaluación por materia y por rfc
            //Verificar que la materia sí puede ser evaluada
            materiaNoEvaluada = materiasNoEvaluadasRepository.buscarPorMateria(grupo.getMateria()).orElse(null);

            if(Objects.isNull(materiaNoEvaluada)){
                evalAlumno = evaluacionAlumnosRepository
                        .busquedaCompleta(rfc, grupo.getIdMateriaCarrera().getMateria().getMateria(), Integer.parseInt(grupo.getGrupo()), grupo.getPeriodo().getPeriodo());

                alumnosEvaluaron += evalAlumno.size();

                //Resultados individuales
                for(EvaluacionAlumnos eval: evalAlumno){

                    for(int i = 0; i < 10; i++){
                        //Realiza 10 iteraciones (son 10 aspectos)
                        listaPromedioAspectos[i] += calcularPuntajeEvaluacion(eval.getRespuestas().substring(RANGOS[i][0], RANGOS[i][1]));
                    }//for Aspectos

                } //for EvaluacionAlumnos
            }


        } //for Grupos

        for(int i = 0; i < 10; i++){
            //Realiza 10 iteraciones (son 10 aspectos)
            evaluacionTablaDocenteDTO = EvaluacionTablaDocenteDTO.builder()
                    .aspecto(listaAspectos.get(i).getDescripcion())
                    .puntaje(Math.round((listaPromedioAspectos[i] / alumnosEvaluaron) * 100f) / 100f)
                    .calificacion(establecerCalificacion(listaPromedioAspectos[i] / alumnosEvaluaron))
                    .build();
            listaEvaluacionTablaDocente.add(evaluacionTablaDocenteDTO);

        }

        return listaEvaluacionTablaDocente;
    }

    public List<IEvaluacionPorMateria> mostrarDatosTablaEvaluacionPorMateria(String periodo, String materia){
        //El periodo de visualización de resultados debe estar habilitado
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodo).orElse(null);

        if(!periodoActivo(periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes())){
            throw new RuntimeException("Periodo no habilitado");
        }

        return evaluacionAlumnosRepository.datosEvaluacionDocentePorMateria(periodo, materia);
    }

    public List<EvaluacionTablaDocenteDTO> procesarResultadosEvaluacionPorMateria(String materia) {
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.obtenerPeriodoActual().orElse(null);

        if(!periodoActivo(periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes())){
            throw new RuntimeException("Periodo no habilitado");
        }
        // Saber cuántos grupos hay por materia
        List<Grupos> gruposMateria = gruposRepository.buscarGruposPorMateria(materia);
        float[] listaPromedioAspectos = new float[10];
        List<Aspectos> listaAspectos  = aspectosRepository.findAll();
        int alumnosEvaluaron = 0;
        List<EvaluacionAlumnos> evalAlumno;
        MateriasNoEvaluadas materiaNoEvaluada;
        //Por cada grupo se desplegará una tabla
        EvaluacionTablaDocenteDTO evaluacionTablaDocenteDTO;
        List<EvaluacionTablaDocenteDTO> listaEvaluacionTablaDocente = new ArrayList<>();

        for (Grupos grupo : gruposMateria) {
            //Hacer búsqueda de evaluación por materia y por rfc

            //Verificar que la materia sí puede ser evaluada
            materiaNoEvaluada = materiasNoEvaluadasRepository.buscarPorMateria(grupo.getIdMateriaCarrera().getMateria().getMateria())
                    .orElse(null);
            System.out.println(materiaNoEvaluada);
            System.out.println(Objects.isNull(materiaNoEvaluada));
            if(Objects.isNull(materiaNoEvaluada)){

                evalAlumno = evaluacionAlumnosRepository
                        .busquedaCompleta(grupo.getRfc().getRfc(), grupo.getIdMateriaCarrera().getMateria().getMateria(), Integer.parseInt(grupo.getGrupo()), grupo.getPeriodo().getPeriodo());
                System.out.println("evalAlumno = " + evalAlumno);
                alumnosEvaluaron += evalAlumno.size();

                //Resultados individuales
                for(EvaluacionAlumnos eval: evalAlumno){
                    for(int i = 0; i < 10; i++){
                        //Realiza 10 iteraciones (son 10 aspectos)
                        listaPromedioAspectos[i] += calcularPuntajeEvaluacion(eval.getRespuestas().substring(RANGOS[i][0], RANGOS[i][1]));
                    }//for Aspectos

                } //for EvaluacionAlumnos
            }


        } //for Grupos

        for(int i = 0; i < 10; i++){
            //Realiza 10 iteraciones (son 10 aspectos)
            evaluacionTablaDocenteDTO = EvaluacionTablaDocenteDTO.builder()
                    .aspecto(listaAspectos.get(i).getDescripcion())
                    .puntaje(Math.round((listaPromedioAspectos[i] / alumnosEvaluaron) * 100f) / 100f)
                    .calificacion(establecerCalificacion(listaPromedioAspectos[i] / alumnosEvaluaron))
                    .build();
            listaEvaluacionTablaDocente.add(evaluacionTablaDocenteDTO);

        }

        return listaEvaluacionTablaDocente;
    }

    /*
    Método para mostrar estadísticas de evaluación docente por departamento
     */
    public List<EstadisticoEvaluacionDocente> estadisticoEvaluacionDocentes (String claveArea, String periodo){

        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodo).orElse(null);

        if(!periodoActivo(periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes())){
            throw new RuntimeException("Periodo no habilitado");
        }

        List<EstadisticoEvaluacionDocente> listaEstadistico;
        List<Personal> docentesPorArea = personalRepository.buscarPersonalPorClaveArea(claveArea);

        listaEstadistico = docentesPorArea.stream().map(docente ->
                EstadisticoEvaluacionDocente.builder()
                        .docente(docente.getNombreEmpleado())
                        .apellidoPaterno(docente.getApellidoPaterno())
                        .apellidoMaterno(docente.getApellidoMaterno())
                        .grupos(mostrarDatosTablaEvaluacionPorDocente(docente.getRfc(), periodo))
                        .build())
                .collect(Collectors.toList());

        return listaEstadistico;
    }



    public List<DocentesEvaluacionDTO> evaluacionPorAlumnos(String claveArea, String periodo){
        //Creación de objeto
        List<DocentesEvaluacionDTO> docentesEvaluacionDTO = new ArrayList<>();
        //Buscar docentes por departamento
        List<Personal> listaPersonal = personalRepository.buscarPersonalPorClaveArea(claveArea);

        //Contar la canidad de alumnos que tiene en total por todos los grupos que tiene el docente
        Integer alumnosInscritos;
        Integer alumnosEvaluaron;

        for(Personal docente: listaPersonal){

            alumnosInscritos = gruposRepository.contarGruposPorDocentePeriodo(docente.getRfc(),periodo);
            alumnosEvaluaron = evaluacionAlumnosRepository.contarEvaluacionesPorDocentePeriodo(docente.getRfc(), periodo);

            DocentesEvaluacionDTO datosDocente = DocentesEvaluacionDTO.builder()
                    .alumnosInscritos(alumnosInscritos)
                    .alumnosEvaluaron(alumnosEvaluaron)
                    .apellidoPaterno(docente.getApellidoPaterno())
                    .apellidoMaterno(docente.getApellidoMaterno())
                    .nombre(docente.getNombreEmpleado())
                    .departamento(docente.getClaveArea().getDescripcionArea())
                    .build();

            docentesEvaluacionDTO.add(datosDocente);

        }

        return docentesEvaluacionDTO;
    }

    public PeriodoEscolar mostrarPeriodoEscolar(String periodo){
        return periodoEscolarRepository.findById(periodo).orElse(null);
    }
    public PeriodoEscolar configurarPeriodoEvaluacionDocente(PeriodoEvaluacionDocenteDTO periodoEvaluacion){

        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodoEvaluacion.getPeriodo())
                .orElseThrow(()-> new EntityNotFoundException("Periodo escolar no habilitado"));

        periodoEscolar.setInicioEncEstudiantil(periodoEvaluacion.getFechaInicial());
        periodoEscolar.setFinEncEstudiantil(periodoEvaluacion.getFechaTermino());

        return periodoEscolarRepository.save(periodoEscolar);
    }

    //Buscar preguntas con aspectos / Llenado manual por alumnos
    public List<PreguntasDTO> buscarPreguntasConAspectos(){
        return preguntasRepository.buscarPreguntasConAspectos('A');
    }

    //Configurar fecha de visualización de resultados de la evaluación docente
    public PeriodoEvaluacionDocenteDTO buscarFechaResultadosEvaluacion(String periodo){
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodo).orElse(null);

        return new PeriodoEvaluacionDocenteDTO(periodo, periodoEscolar.getInicioCalDocentes(), periodoEscolar.getFinCalDocentes());
    }

    public PeriodoEscolar establecerFechaResultadosEvaluacion(PeriodoEvaluacionDocenteDTO periodoResultados){
        PeriodoEscolar periodoEscolar = periodoEscolarRepository.findById(periodoResultados.getPeriodo())
                .orElse(null);
        periodoEscolar.setInicioCalDocentes(periodoResultados.getFechaInicial());
        periodoEscolar.setFinCalDocentes(periodoResultados.getFechaTermino());

        return periodoEscolarRepository.save(periodoEscolar);
    }




    /*
    MÉTODOS SECUNDARIOS
     */
    public static float porcentajeEvaluacion(float totalAlumnos, float alumnosEvaluaron) {
        return alumnosEvaluaron * 100 / totalAlumnos;
    }

    public static float calcularPuntajeEvaluacion(String respuestas){
        String respuestasInd[] = respuestas.split("");
        float suma = 0;

        for(String respuesta: respuestasInd){
            suma += Integer.parseInt(respuesta);
        }
        return suma / respuestas.length();
    }

    public static String establecerCalificacion(float puntaje){
        if(puntaje > 0 && puntaje < 2){
            return "MALO";
        } else if(puntaje >= 2 && puntaje < 3){
            return "BUENO";
        } else if(puntaje >= 3 && puntaje <= 4){
            return "MUY BUENO";
        } else if (puntaje > 4 && puntaje <= 5) {
            return "EXCELENTE";
        }

        return null;
    }

    public EvaluacionAlumnos procesarObjetoEvaluacion(EvaluacionRequest evaluacionInd){
        EvaluacionAlumnosPK llavePrimaria = EvaluacionAlumnosPK.builder()
                .materia(evaluacionInd.getMateria())
                .periodo(evaluacionInd.getPeriodo())
                .noControl(evaluacionInd.getNoControl())
                .consecutivo(evaluacionInd.getConsecutivo())
                .build();

        EvaluacionAlumnos evaluacion = EvaluacionAlumnos.builder()
                .evaluacionAlumnosPK(llavePrimaria)
                .grupo(evaluacionInd.getGrupo())
                .docente(evaluacionInd.getDocente())
                .departamento(evaluacionInd.getDepartamento())
                .encuesta(evaluacionInd.getEncuesta())
                .respuestas(evaluacionInd.getRespuestas())
                .respAbierta(evaluacionInd.getRespAbierta())
                .usuario(evaluacionInd.getUsuario())
                .fechaHoraEvaluacion(evaluacionInd.getFechaHoraEvaluacion())
                .build();


        return evaluacion;
    }

    public boolean periodoActivo(Date fechaInicio, Date fechaFinal){
        Date fechaActual = new Date();
        return fechaActual.after(fechaInicio) && fechaActual.before(fechaFinal);
    }
}
