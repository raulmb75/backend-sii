package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.MateriasCarreras;
import com.sii.aspirantes.aspirantes.Repository.MateriasCarrerasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriasCarrerasService {

    private final MateriasCarrerasRepository materiasCarrerasRepository;

    public MateriasCarrerasService(MateriasCarrerasRepository materiasCarrerasRepository) {
        this.materiasCarrerasRepository = materiasCarrerasRepository;
    }

    public List<MateriasCarreras> buscarTodasLasCarrerasPorMaterias(){
        return materiasCarrerasRepository.findAll();
    }
}
