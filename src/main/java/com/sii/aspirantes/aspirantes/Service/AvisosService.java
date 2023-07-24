package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import com.sii.aspirantes.aspirantes.Entity.Avisos;
import com.sii.aspirantes.aspirantes.Entity.Organigrama;
import com.sii.aspirantes.aspirantes.Repository.AccesoRepository;
import com.sii.aspirantes.aspirantes.Repository.AvisosRepository;
import com.sii.aspirantes.aspirantes.Repository.OrganigramaRepository;
import com.sii.aspirantes.aspirantes.dto.AvisoRequest;
import com.sii.aspirantes.aspirantes.dto.AvisoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisosService {

    private final AvisosRepository avisosRepository;
    private final AccesoRepository accesoRepository;
    private final OrganigramaRepository organigramaRepository;

    public AvisosService(AvisosRepository avisosRepository,
                         AccesoRepository accesoRepository,
                         OrganigramaRepository organigramaRepository) {
        this.avisosRepository = avisosRepository;
        this.accesoRepository = accesoRepository;
        this.organigramaRepository = organigramaRepository;
    }

    public List<AvisoResponse> mostrarListaAvisosPorUsuario(String usuario){
         return avisosRepository.buscarAvisosPorUsuario(usuario);
    }

    public AvisoResponse mostrarAvisoPorId(String usuario, Long id){
        return avisosRepository.buscarAvisosPorUsuarioId(usuario, id);
    }

    public AvisoResponse crearNuevoAviso(AvisoRequest avisoRequest){
        Acceso usuario = accesoRepository.findById(avisoRequest.getUsuario()).orElse(null);
        Organigrama area = organigramaRepository.findById(avisoRequest.getDepartamento()).orElse(null);

        Avisos aviso = Avisos.builder()
                .titulo(avisoRequest.getTitulo())
                .contenido(avisoRequest.getContenido())
                .usuario(usuario)
                .seccion(avisoRequest.getSeccion())
                .build();

        return avisosRepository.buscarAvisosPorUsuarioId(usuario.getUsuario(), avisosRepository.save(aviso).getId());
    }

    public AvisoResponse modificarInformacionAviso(Long id, AvisoRequest avisoRequest){
        Avisos avisoRegistrado = avisosRepository.findById(id).get();

        Acceso usuario = accesoRepository.findById(avisoRequest.getUsuario()).orElse(null);
        Organigrama area = organigramaRepository.findById(avisoRequest.getDepartamento()).orElse(null);

        avisoRegistrado = Avisos.builder()
                .titulo(avisoRequest.getTitulo())
                .contenido(avisoRequest.getContenido())
                .usuario(usuario)
                .seccion(avisoRequest.getSeccion())
                .build();

        return avisosRepository.buscarAvisosPorUsuarioId(usuario.getUsuario(), avisosRepository.save(avisoRegistrado).getId());

    }

    public void removerAviso(Long id){
        avisosRepository.deleteById(id);
    }
}
