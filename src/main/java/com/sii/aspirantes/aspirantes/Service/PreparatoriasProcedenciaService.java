package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.PreparatoriasProcedencia;
import com.sii.aspirantes.aspirantes.Repository.PreparatoriasProcedenciaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PreparatoriasProcedenciaService {

    private final PreparatoriasProcedenciaRepository preparatoriasProcedenciaRepository;

    public PreparatoriasProcedenciaService(PreparatoriasProcedenciaRepository preparatoriasProcedenciaRepository) {
        this.preparatoriasProcedenciaRepository = preparatoriasProcedenciaRepository;
    }

    public List<PreparatoriasProcedencia> mostrarPreparatoriasDeProcedencia(){
        return preparatoriasProcedenciaRepository.findAll();
    }

    public PreparatoriasProcedencia buscarPreparatoriaPorClave(String clave){
        PreparatoriasProcedencia preparatoriasProcedencia = preparatoriasProcedenciaRepository.findById(clave)
                .orElseThrow(()->
                new EntityNotFoundException("Preparatoria con clave " + clave + " no encontrada"));

        return preparatoriasProcedencia;
    }

    //Buscar por entidad federativa
    public List<PreparatoriasProcedencia> buscarPreparatoriasPorEntidadFederativa(Integer entidadFederativa){
        return preparatoriasProcedenciaRepository.buscarPreparatoriasPorEntidadFederativa(entidadFederativa);
    }
    //Buscar por municipio
    public List<PreparatoriasProcedencia> buscarPreparatoriasPorMunicipio(String municipio){
        return preparatoriasProcedenciaRepository.buscarPreparatoriasPorMunicipio(municipio);
    }
    //Buscar por servicio
    public List<PreparatoriasProcedencia> buscarPreparatoriasPorServicio(String servicio){
        return preparatoriasProcedenciaRepository.buscarPreparatoriasPorServicio(servicio);
    }
    //Buscar por sostenimiento
    public List<PreparatoriasProcedencia> buscarPreparatoriasPorSostenimiento(String sostenimiento){
        return preparatoriasProcedenciaRepository.buscarPreparatoriasPorSostenimiento(sostenimiento);
    }
}
