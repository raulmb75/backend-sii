package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Carrera;
import com.sii.aspirantes.aspirantes.Repository.CarreraRepository;
import com.sii.aspirantes.aspirantes.dto.evaluacion.ListaCarrerasEvaluanDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<Carrera> buscarTodasCarreras(){
        return carreraRepository.findAll();
    }

    public Carrera buscarCarreraPorReticula(Integer reticula){
        Carrera carrera = carreraRepository.findById(reticula)
                .orElseThrow(()->
                        new EntityNotFoundException("Carrera con retícula " + reticula + " no encontrada"));

        return carrera;
    }

    public List<Carrera> buscarCarreraPorNivelEscolar(Character nivelEscolar){
        return carreraRepository.buscarCarreraPorNivelEscolar(nivelEscolar);
    }

    public List<Carrera> buscarCarreraPorNombre(String nombre){
        return carreraRepository.buscarCarrerasPorNombre(nombre);
    }

    public List<Carrera> buscarCarreraPorCreditosTotales(int creditos){
        return carreraRepository.buscarCarreraPorCreditos(creditos);
    }

    //Lista desplegable para las carreras que evaúan
    public List<ListaCarrerasEvaluanDTO> buscarCarreras(){
        return carreraRepository.buscarCarreras();
    }

}
