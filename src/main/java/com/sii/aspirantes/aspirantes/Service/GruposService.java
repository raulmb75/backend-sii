package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.Entity.Horarios;
import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import com.sii.aspirantes.aspirantes.Repository.GruposRepository;
import com.sii.aspirantes.aspirantes.Repository.HorariosRepository;
import com.sii.aspirantes.aspirantes.Repository.MateriasCarrerasRepository;
import com.sii.aspirantes.aspirantes.dto.GruposCarreraDTO;
import com.sii.aspirantes.aspirantes.dto.GruposMateriasDTO;
import com.sii.aspirantes.aspirantes.dto.HorarioSemana;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GruposService {

    private final GruposRepository gruposRepository;
    private final HorariosRepository horariosRepository;
    private final MateriasCarrerasRepository materiasCarrerasRepository;

    public GruposService(GruposRepository gruposRepository,
                         HorariosRepository horariosRepository,
                         MateriasCarrerasRepository materiasCarrerasRepository) {
        this.gruposRepository = gruposRepository;
        this.horariosRepository = horariosRepository;
        this.materiasCarrerasRepository = materiasCarrerasRepository;
    }

    public List<Grupos> buscarTodosGrupos(){
        return gruposRepository.findAll();
    }

    public Grupos buscarGrupoPorId(Integer idGrupo){
        Grupos grupo = gruposRepository.findById(idGrupo)
                .orElseThrow(()->
                        new EntityNotFoundException(String.format("Grupo con id %i no encontrado", idGrupo)));
        return grupo;
    }

    public Grupos registrarGrupo(Grupos grupo){
        return gruposRepository.save(grupo);
    }

    public Grupos actualizarDatosGrupo(Integer idGrupo, Grupos grupo){
        Grupos grupoRegistrado = gruposRepository.findById(idGrupo).orElse(null);

        if(grupoRegistrado != null) {
            return gruposRepository.save(grupo);
        }

        throw new EntityNotFoundException(String.format("Grupo con id %i no encontrado", idGrupo));
    }

    public void eliminarGrupo(Integer idGrupo){
        gruposRepository.deleteById(idGrupo);
    }

    //Buscar por materia
    public List<Grupos> buscarGruposPorMateria(String materia){
        return gruposRepository.buscarGruposPorMateria(materia);
    }

    public List<GruposCarreraDTO> buscarGruposPorCarrera(Integer reticula,
                                                         String periodo,
                                                         String claveArea,
                                                         Integer semestre){
        //Este método funciona para dos métodos de controladores

        //Definiciones de datos
        List<GruposCarreraDTO> gruposCarreraDTOS = new ArrayList<>();
        GruposCarreraDTO grupoDTO;
        List<Grupos> listaGrupos;
        List<Horarios> listaHorarios;
        HorarioSemana horarioSemana = new HorarioSemana();
        List<MateriasCarreras> materiasPorCarrera = new ArrayList<>();

        //Obtener las materias por la materia seleccionada
        if(claveArea == null){
            //Busca los grupos por carrera
            materiasPorCarrera = materiasCarrerasRepository.obtenerMateriasPorCarrera(reticula);
        } else if (claveArea == null && semestre != null) {
            materiasPorCarrera = materiasCarrerasRepository.obtenerMateriasPorCarreraSemestre(reticula, semestre);
        } else if (claveArea != null){
            //Busca los grupos por departamento / área
            materiasPorCarrera = materiasCarrerasRepository.obtenerMateriasPorDepartamento(claveArea);
        } else if (claveArea != null && semestre != null) {
            materiasPorCarrera = materiasCarrerasRepository.obtenerMateriasPorDepartamentoSemestre(claveArea, semestre);
        }

        //Por cada materia se obtienen su(s) grupo(s) (1,2,3, etc.)
        for(MateriasCarreras materia: materiasPorCarrera){
            //Busqueda de grupo por materia
            //Un grupo puede tener varios horarios
            listaGrupos = gruposRepository.buscarGruposPorMateria(materia.getMateria().getMateria());
            //Por cada grupo (1,2, etc.) buscar su horario. Buscar por grupo, materia, rfc
            for(Grupos grupo: listaGrupos){
                //Agregarlos a una lista
                listaHorarios = horariosRepository.busquedaCompleta(periodo, grupo.getMateria(), grupo.getGrupo(), grupo.getRfc().getRfc());
                //Agrupar el horario en un mismo objeto (por los diferentes días de la semana)
                if (!listaHorarios.isEmpty()) {

                    List<HorarioSemana> listaHorarioSemana = new ArrayList<>();

                    for(Horarios horario: listaHorarios){
                        horarioSemana = HorarioSemana.builder()
                                .dia(horario.getDiaSemana())
                                .horaInicial(horario.getHoraInicial())
                                .horaFinal(horario.getHoraFinal())
                                .aula(horario.getAula())
                                .build();
                        listaHorarioSemana.add(horarioSemana);
                    }

                    grupoDTO = GruposCarreraDTO.builder()
                            .materia(listaHorarios.get(0).getMateria().getMateria())
                            .nombreMateria(grupo.getIdMateriaCarrera().getMateria().getNombreCompletoMateria())
                            .grupo(listaHorarios.get(0).getGrupo())
                            .exclCar(null)
                            .capacidad(listaHorarios.get(0).getIdGrupo().getCapacidadGrupo())
                            .alumnosInsgritos(listaHorarios.get(0).getIdGrupo().getAlumnosInscritos())
                            .rfc(listaHorarios.get(0).getRfc().getRfc())
                            .nombreDocente(listaHorarios.get(0).getRfc().getNombreEmpleado())
                            .apellidoPaterno(listaHorarios.get(0).getRfc().getApellidoPaterno())
                            .apellidoMaterno(listaHorarios.get(0).getRfc().getApellidoMaterno())
                            .horarioSemana(listaHorarioSemana)
                            .build();
                    gruposCarreraDTOS.add(grupoDTO);
                }
            }
        }
        return gruposCarreraDTOS;
    }//Fin método

    //Buscar por periodo
    public List<Grupos> buscarGruposPorPeriodo(String periodo){
        return gruposRepository.buscarGruposPorPeriodo(periodo);
    }

    //Buscar por personal
    public List<Grupos> buscarGruposPorDocente(String rfc){
        return gruposRepository.buscarGruposPorDocente(rfc);
    }

    public List<GruposMateriasDTO> buscarGrupos(Integer reticula){
        return gruposRepository.buscarGruposConMateria(reticula);
    }
}
