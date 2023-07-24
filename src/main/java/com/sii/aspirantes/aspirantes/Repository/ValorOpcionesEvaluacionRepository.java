package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.ValorOpcionesEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorOpcionesEvaluacionRepository extends JpaRepository<ValorOpcionesEvaluacion, Character> {
}
