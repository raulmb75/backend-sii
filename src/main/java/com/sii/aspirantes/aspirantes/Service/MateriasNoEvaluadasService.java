package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Grupos;
import com.sii.aspirantes.aspirantes.Entity.MateriasNoEvaluadas;
import com.sii.aspirantes.aspirantes.Repository.GruposRepository;
import com.sii.aspirantes.aspirantes.Repository.MateriaRepository;
import com.sii.aspirantes.aspirantes.Repository.MateriasNoEvaluadasRepository;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasDTO;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class MateriasNoEvaluadasService {

    private MateriasNoEvaluadasRepository materiasNoEvaluadasRepository;
    private MateriaRepository materiaRepository;
    private final GruposRepository gruposRepository;

    public MateriasNoEvaluadasService(MateriasNoEvaluadasRepository materiasNoEvaluadasRepository,
                                      MateriaRepository materiaRepository, GruposRepository gruposRepository) {
        this.materiasNoEvaluadasRepository = materiasNoEvaluadasRepository;
        this.materiaRepository = materiaRepository;
        this.gruposRepository = gruposRepository;
    }

    public List<MateriasNoEvaluadasDTO> buscarMateriasNoEvaluadas(){
        return materiasNoEvaluadasRepository.buscarMateriasNoEvaluadas();
    }

    public MateriasNoEvaluadas registrarMateriaNoEvaluada(MateriasNoEvaluadasRequest materiasNoEvaluadas) throws Exception {
        MateriasNoEvaluadas materiaNoEvaluada;
        Grupos grupoMateria = gruposRepository.buscarGrupoConMateria(materiasNoEvaluadas.getMateria(), materiasNoEvaluadas.getGrupo())
                .orElseThrow(()->
                        new EntityNotFoundException("Materia/Grupo no registrado: "+materiasNoEvaluadas.getMateria()));

        if(Objects.nonNull(materiasNoEvaluadasRepository.buscarPorGrupo(grupoMateria))){
            throw new Exception("El grupo ya se ha omitido");
        }

        materiaNoEvaluada = MateriasNoEvaluadas.builder()
                .grupo(grupoMateria)
                .evaluacion('A')
                .descripcionOmision(materiasNoEvaluadas.getDescripcionOmision())
                .build();


        return materiasNoEvaluadasRepository.save(materiaNoEvaluada);
    }

    public void removerMateriaNoEvaluada(Long id){
        materiasNoEvaluadasRepository.deleteById(id);
    }
}
