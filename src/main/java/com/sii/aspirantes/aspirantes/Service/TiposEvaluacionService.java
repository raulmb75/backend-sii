package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.TiposEvaluacion;
import com.sii.aspirantes.aspirantes.Repository.TiposEvaluacionRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TiposEvaluacionService {

    private final TiposEvaluacionRepository tiposEvaluacionRepository;

    public TiposEvaluacionService(TiposEvaluacionRepository tiposEvaluacionRepository) {
        this.tiposEvaluacionRepository = tiposEvaluacionRepository;
    }

    public List<TiposEvaluacion> mostrarTiposEvaluacion(){
        return tiposEvaluacionRepository.findAll();
    }

    public TiposEvaluacion agregarTipoEvaluacion(TiposEvaluacion evaluacion){
        return tiposEvaluacionRepository.save(evaluacion);
    }

    public TiposEvaluacion modificarTiposEvaluacion(Character encuesta, TiposEvaluacion tiposEvaluacion){
        TiposEvaluacion tipos = tiposEvaluacionRepository.findById(encuesta)
                .orElseThrow(()->
                        new EntityNotFoundException("Encuesta " + encuesta +  " no registrada"));

        tipos.setDescripcion(tiposEvaluacion.getDescripcion());
        tipos.setTipo(tiposEvaluacion.getTipo());

        return tiposEvaluacionRepository.save(tipos);
    }

    public void eliminarTipoEvaluacion(Character id){
        tiposEvaluacionRepository.deleteById(id);
    }
}
