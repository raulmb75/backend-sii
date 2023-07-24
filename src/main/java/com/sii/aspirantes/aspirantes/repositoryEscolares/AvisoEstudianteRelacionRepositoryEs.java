package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.AvisoEstudiantePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoEstudianteRelacionRepositoryEs extends JpaRepository <AvisoEstudiantePK, Long> {


}
