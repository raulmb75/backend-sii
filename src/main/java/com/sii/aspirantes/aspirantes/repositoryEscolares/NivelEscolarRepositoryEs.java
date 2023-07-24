package com.sii.aspirantes.aspirantes.repositoryEscolares;

import com.sii.aspirantes.aspirantes.entityEscolares.NivelEscolarEs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelEscolarRepositoryEs extends JpaRepository <NivelEscolarEs, Character> {
}
