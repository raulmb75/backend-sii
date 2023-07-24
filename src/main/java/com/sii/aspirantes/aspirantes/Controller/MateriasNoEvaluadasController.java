package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.MateriasNoEvaluadas;
import com.sii.aspirantes.aspirantes.Service.GruposService;
import com.sii.aspirantes.aspirantes.Service.MateriaService;
import com.sii.aspirantes.aspirantes.Service.MateriasNoEvaluadasService;
import com.sii.aspirantes.aspirantes.dto.GruposMateriasDTO;
import com.sii.aspirantes.aspirantes.dto.ListaMateriasDTO;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasDTO;
import com.sii.aspirantes.aspirantes.dto.evaluacion.MateriasNoEvaluadasRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dda/evaluacion/materias_no_evaluadas")
public class MateriasNoEvaluadasController {

    private final MateriasNoEvaluadasService materiasNoEvaluadasService;
    private final MateriaService materiaService;
    private final GruposService gruposService;

    public MateriasNoEvaluadasController(MateriasNoEvaluadasService materiasNoEvaluadasService,
                                         MateriaService materiaService, GruposService gruposService) {
        this.materiasNoEvaluadasService = materiasNoEvaluadasService;
        this.materiaService = materiaService;
        this.gruposService = gruposService;
    }

    @GetMapping
    public ResponseEntity<List<MateriasNoEvaluadasDTO>> mostrarMateriasNoEvaluadas(){
        return new ResponseEntity<>(materiasNoEvaluadasService.buscarMateriasNoEvaluadas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MateriasNoEvaluadas> registrarMateriaNoEvaluada(@RequestBody MateriasNoEvaluadasRequest materia) throws Exception {
        return new ResponseEntity<>(materiasNoEvaluadasService.registrarMateriaNoEvaluada(materia), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerMateriaNoEvaluada(@PathVariable Long id){
        materiasNoEvaluadasService.removerMateriaNoEvaluada(id);
        return new ResponseEntity<>("Materia removida con éxito", HttpStatus.OK);
    }

    @GetMapping("lista-materias/{reticula}")
    public ResponseEntity<List<GruposMateriasDTO>> mostrarMateriasPorReticula(@PathVariable Integer reticula){
        return new ResponseEntity<>(gruposService.buscarGrupos(reticula), HttpStatus.OK);
    }
}
