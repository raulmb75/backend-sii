package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.PeriodoEscolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodoEscolarRepository extends JpaRepository<PeriodoEscolar, String> {

    //Obtener periodo activo
    @Query(value = "SELECT periodo FROM periodo_escolar WHERE status = '0'", nativeQuery = true)
    String obtenerPeriodoEscolarActual();

    @Query(value = "FROM PeriodoEscolar WHERE status = '0'")
    Optional<PeriodoEscolar> obtenerPeriodoActual();


}
