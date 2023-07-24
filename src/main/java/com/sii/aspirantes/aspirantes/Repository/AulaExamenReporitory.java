package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.AulaExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaExamenReporitory extends JpaRepository<AulaExamen, Long> {
    @Query(value = "FROM AulaExamen WHERE aula = ?1")
    List<AulaExamen> buscarPorAula(String aula);
}
