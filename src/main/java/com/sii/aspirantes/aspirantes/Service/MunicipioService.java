package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Municipio;
import com.sii.aspirantes.aspirantes.Entity.MunicipioPK;
import com.sii.aspirantes.aspirantes.Repository.MunicipioRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MunicipioService {
    private final MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public Municipio buscarPorClave(MunicipioPK municipioPK){
        Municipio municipio = municipioRepository.findById(municipioPK)
                .orElseThrow(() -> new EntityNotFoundException("Registro con clave " + municipioPK + " no encontrado"));

        return municipio;
    }

    public List<Municipio> buscarMunicipiosPorEstado(Integer claveEntidad){
        return municipioRepository.buscarMunicipiosPorEstado(claveEntidad);
    }
}
