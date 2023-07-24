package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.Organigrama;
import com.sii.aspirantes.aspirantes.Service.OrganigramaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//No implementado por ningún rol
@RestController
@RequestMapping(value = "/api/organigrama")
public class OrganigramaController {

    private final OrganigramaService organigramaService;

    public OrganigramaController(OrganigramaService organigramaService) {
        this.organigramaService = organigramaService;
    }

    @GetMapping
    public List<Organigrama> mostrarAreas(){
        return organigramaService.buscarTodos();
    }

    @GetMapping(value = "/{clave}")
    public Organigrama mostrarAreaPorClave(@PathVariable String clave){
        return organigramaService.buscarPorClaveArea(clave);
    }

    @GetMapping(value = "/tipo-area/{tipo}")
    public List<Organigrama> mostrarAreasPorTipo(@PathVariable Character tipo){
        return organigramaService.buscarAreaPorTipo(tipo);
    }

    @GetMapping(value = "/nivel/{nivel}")
    public List<Organigrama> mostrarAreasPorNivel(@PathVariable Integer nivel){
        return organigramaService.buscarAreaPorNivel(nivel);
    }

    @GetMapping(value = "/dependencia/{dependencia}")
    public List<Organigrama> mostrarAreasPorDependencia(@PathVariable String dependencia){
        return organigramaService.buscarAreaPorDependencia(dependencia);
    }

    @GetMapping(value = "/descripcion/{descripcion}")
    public Organigrama mostrarAreaPorDescripcion(@PathVariable String descripcion){
        return organigramaService.buscarAreaPorDescripcion(descripcion);
    }
}
