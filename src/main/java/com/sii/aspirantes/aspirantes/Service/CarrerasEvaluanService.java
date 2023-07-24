package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Carrera;
import com.sii.aspirantes.aspirantes.Entity.CarrerasEvaluan;
import com.sii.aspirantes.aspirantes.Repository.CarreraRepository;
import com.sii.aspirantes.aspirantes.Repository.CarrerasEvaluanRepository;
import com.sii.aspirantes.aspirantes.dto.evaluacion.CarrerasEvaluanDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarrerasEvaluanService {

    private final CarrerasEvaluanRepository carrerasEvaluanRepository;
    private final CarreraRepository carreraRepository;

    public CarrerasEvaluanService(CarrerasEvaluanRepository carrerasEvaluanRepository,
                                  CarreraRepository carreraRepository) {
        this.carrerasEvaluanRepository = carrerasEvaluanRepository;
        this.carreraRepository = carreraRepository;
    }

    public List<CarrerasEvaluanDTO> mostrarCarrerasEvaluan(){
        return carrerasEvaluanRepository.buscarCarrerasEvaluan();
    }

    public CarrerasEvaluan agregarCarreraEvalua(Integer reticula){
        CarrerasEvaluan carrerasEvaluan = new CarrerasEvaluan();
        Carrera carrera = carreraRepository.findById(reticula)
                .orElseThrow(()->
                        new EntityNotFoundException("Carrera con reticula " + reticula + " no encontrada"));

        carrerasEvaluan.setCarrera(carrera);
        carrerasEvaluan.setEvaluacion('A');

        return carrerasEvaluanRepository.save(carrerasEvaluan);
    }

    public void eliminarCarreraEvalua(Long id){
        carrerasEvaluanRepository.deleteById(id);
    }
}
