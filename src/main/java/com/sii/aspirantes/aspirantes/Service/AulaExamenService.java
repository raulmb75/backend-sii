package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.AulaExamen;
import com.sii.aspirantes.aspirantes.Entity.Carrera;
import com.sii.aspirantes.aspirantes.Repository.AulaExamenReporitory;
import com.sii.aspirantes.aspirantes.Repository.CarreraRepository;
import com.sii.aspirantes.aspirantes.dto.AulaExamenDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaExamenService {

    private final AulaExamenReporitory aulaExamenReporitory;
    private final CarreraRepository carreraRepository;

    public AulaExamenService(AulaExamenReporitory aulaExamenReporitory, CarreraRepository carreraRepository) {
        this.aulaExamenReporitory = aulaExamenReporitory;
        this.carreraRepository = carreraRepository;
    }

    public List<AulaExamenDTO> buscarAulas(){
        List<AulaExamen> aulaExamenList =  aulaExamenReporitory.findAll();

        List<AulaExamenDTO> aulaExamenDTOList = aulaExamenList.stream()
                .map(aula ->
                        AulaExamenDTO.builder()
                        .disponibilidad(aula.getDisponibilidad())
                        .asignados(aula.getAsignados())
                        .capacidad(aula.getCapacidad())
                        .carrera(aula.getCarrera().getNombreCarrera())
                        .aula(aula.getAula())
                        .build()).collect(Collectors.toList());

        return aulaExamenDTOList;
    }

    public AulaExamenDTO registrarAula(AulaExamenDTO aulaExamenDTO){
        Carrera carrera = carreraRepository.buscarCarreraPorNombre(aulaExamenDTO.getCarrera())
                .orElse(null);

        short disponibilidad = (short) (aulaExamenDTO.getDisponibilidad() - aulaExamenDTO.getAsignados());

        AulaExamen aulaExamen = AulaExamen.builder()
                .aula(aulaExamenDTO.getAula())
                .capacidad(aulaExamenDTO.getCapacidad())
                .asignados(aulaExamenDTO.getAsignados())
                .disponibilidad(disponibilidad)
                .carrera(carrera)
                .build();

        aulaExamenReporitory.save(aulaExamen);
        return aulaExamenDTO;
    }

    public void eliminarAula(Long id){
        aulaExamenReporitory.deleteById(id);
    }
}
