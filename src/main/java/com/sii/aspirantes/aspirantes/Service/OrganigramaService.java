package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Organigrama;
import com.sii.aspirantes.aspirantes.Repository.OrganigramaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganigramaService {

    private final OrganigramaRepository organigramaRepository;

    public OrganigramaService(OrganigramaRepository organigramaRepository){
        this.organigramaRepository = organigramaRepository;
    }

    public List<Organigrama> buscarTodos(){
        return organigramaRepository.findAll();
    }

    public Organigrama buscarPorClaveArea(String claveArea){
        Organigrama organigrama= organigramaRepository.findById(claveArea)
                .orElseThrow(()->
                        new EntityNotFoundException("Área " + claveArea + " no encontrada"));

        return organigrama;
    }

    public Organigrama buscarAreaPorDescripcion(String descripcion){
        return organigramaRepository.buscarAreaPorDescripcion(descripcion);
    }

    public List<Organigrama> buscarAreaPorNivel(Integer nivel){
        return organigramaRepository.buscarAreaPorNivel(nivel);
    }

    public List<Organigrama> buscarAreaPorTipo(Character tipoArea){
        return organigramaRepository.buscarAreaPorTipo(tipoArea);
    }

    public List<Organigrama> buscarAreaPorDependencia(String areaDepende){
        return organigramaRepository.buscarAreaPorDependencia(areaDepende);
    }
}
