package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.ValorOpcionesEvaluacion;
import com.sii.aspirantes.aspirantes.Repository.ValorOpcionesEvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValorOpcionesEvaluacionService {

    private final ValorOpcionesEvaluacionRepository valorOpcionesEvaluacionRepository;

    public ValorOpcionesEvaluacionService(ValorOpcionesEvaluacionRepository valorOpcionesEvaluacionRepository) {
        this.valorOpcionesEvaluacionRepository = valorOpcionesEvaluacionRepository;
    }

    public List<ValorOpcionesEvaluacion> buscarOpciones(){
        return valorOpcionesEvaluacionRepository.findAll();
    }

    public ValorOpcionesEvaluacion agregarOpcion(ValorOpcionesEvaluacion opcion){
        return valorOpcionesEvaluacionRepository.save(opcion);
    }

    public void eliminarOpcion(Character opcion){
        valorOpcionesEvaluacionRepository.deleteById(opcion);
    }
}
