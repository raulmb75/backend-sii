package com.sii.aspirantes.aspirantes.Controller;

import com.sii.aspirantes.aspirantes.Entity.*;
import com.sii.aspirantes.aspirantes.Service.AspiranteService;
import com.sii.aspirantes.aspirantes.Service.PreparatoriasProcedenciaService;
import com.sii.aspirantes.aspirantes.Utils.JWTUtil;
import com.sii.aspirantes.aspirantes.dto.aspirante.DatosAspiranteDTO;
import com.sii.aspirantes.aspirantes.dto.aspirante.DatosRegistroAspiranteDTO;
import com.sii.aspirantes.aspirantes.dto.aspirante.NotificacionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es la definición del controlador para los datos socioeconómicos de los Aspirantes
 * solo muestra las listas con datos
 */
@RestController
@RequestMapping("/api/aspirante")
public class AspiranteController {

    private final AspiranteService aspiranteService;
    private final PreparatoriasProcedenciaService preparatoriasProcedenciaService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private static final String PROTECTED_URL = "/protected";

    public AspiranteController(AspiranteService aspiranteService, PreparatoriasProcedenciaService preparatoriasProcedenciaService, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.aspiranteService = aspiranteService;
        this.preparatoriasProcedenciaService = preparatoriasProcedenciaService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(PROTECTED_URL+"/{noSolicitud}")
    public DatosAspiranteDTO getAspirante(@PathVariable(value = "noSolicitud") Long noSolicitud){
        return aspiranteService.obtenerAspirantePorNoSolicitud(noSolicitud);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Aspirante> registrarAccessoAspirante(@RequestBody DatosRegistroAspiranteDTO aspirante) throws Exception {
        Aspirante aspirante1 = aspiranteService.guardarAspirante(aspirante);
        String usuario = "ASPIRANTE " + aspirante1.getNoSolicitud();
        String token = jwtUtil.generateToken(usuario);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);
        return new ResponseEntity<>(aspirante1, responseHeaders, HttpStatus.OK);
    }

    @PutMapping(PROTECTED_URL+"/registrar/datos_generales/{id}")
    public ResponseEntity<Aspirante> registrarDatosGenerales(@PathVariable Long id,
                                                             @RequestBody DatosAspiranteDTO aspirante) throws Exception {

        return new ResponseEntity<>(aspiranteService.registrarDatosGenerales(id, aspirante), HttpStatus.OK);
    }

    @GetMapping(PROTECTED_URL+"/preparatorias/entidad/{entidadFederativa}")
    public ResponseEntity<List<PreparatoriasProcedencia>> buscarPreparatoriasDeProcedencia(@PathVariable Integer entidadFederativa){
        return new ResponseEntity<>(preparatoriasProcedenciaService.buscarPreparatoriasPorEntidadFederativa(entidadFederativa), HttpStatus.OK);
    }

    @GetMapping(PROTECTED_URL+"/notificaciones/{noSolicitud}")
    public ResponseEntity<List<NotificacionDTO>> notificacionesDelAspirante(@PathVariable Long noSolicitud){
        return new ResponseEntity<>(aspiranteService.notificacionesDelAspirante(noSolicitud), HttpStatus.OK);
    }





    @GetMapping(PROTECTED_URL+"/lista/entidad_federativa")
    public List<EntidadFederativa> mostrarEntidadFederativa(){
        return aspiranteService.obtenerEntidadFederativa();
    }

    @GetMapping(PROTECTED_URL+"/lista/carreras")
    public List<Carrera> mostrarCarreras(){
        return aspiranteService.obtenerCarreras();
    }
}
