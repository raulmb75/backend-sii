package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.CarrerasEvaluan;
import com.sii.aspirantes.aspirantes.dto.evaluacion.CarrerasEvaluanDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrerasEvaluanRepository extends JpaRepository<CarrerasEvaluan, Long> {

    @Query(value = "SELECT new com.sii.aspirantes.aspirantes.dto.evaluacion.CarrerasEvaluanDTO (c.carrera.reticula, c.carrera.carrera, c.carrera.nombreCarrera, c.evaluacion) FROM CarrerasEvaluan c")
    List<CarrerasEvaluanDTO> buscarCarrerasEvaluan();

}
