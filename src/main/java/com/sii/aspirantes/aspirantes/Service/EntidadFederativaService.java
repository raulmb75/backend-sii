package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.EntidadFederativa;
import com.sii.aspirantes.aspirantes.Repository.EntidadFederativaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EntidadFederativaService {

    private final EntidadFederativaRepository entidadFederativaRepository;

    public EntidadFederativaService(EntidadFederativaRepository entidadFederativaRepository) {
        this.entidadFederativaRepository = entidadFederativaRepository;
    }

    public List<EntidadFederativa> buscarEntidadesFederativas(){
        return entidadFederativaRepository.findAll();
    }

    public EntidadFederativa buscarPorNoEntidad(Integer entidad_federativa){
        EntidadFederativa entidadFederativa = entidadFederativaRepository.findById(entidad_federativa)
                .orElseThrow(()->
                new EntityNotFoundException("Dato con referencia "
                        + entidad_federativa + " no encontrado"));

        return entidadFederativa;
    }
}
