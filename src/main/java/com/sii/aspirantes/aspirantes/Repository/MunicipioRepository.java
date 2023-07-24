package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Municipio;
import com.sii.aspirantes.aspirantes.Entity.MunicipioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, MunicipioPK> {

    @Query(value = "FROM Municipio m WHERE m.municipioPK.claveEntidad = ?1 " +
            "ORDER BY m.nombreMunicipio ASC")
    List<Municipio> buscarMunicipiosPorEstado(Integer claveEntidad);
}
