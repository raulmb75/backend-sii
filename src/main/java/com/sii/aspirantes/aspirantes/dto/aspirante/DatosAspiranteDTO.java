package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosAspiranteDTO {
    private Long noSolicitud;
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer nip;
    private Integer periodo;
    private Date fechaNacimiento;
    private String estadoCivil;
    private Character genero; // H - M
    private String nacionalidad;
    private String curp;
    private Integer carreraOp1;
    private Integer carreraOp2;
    private String clavePreparatoria;
    private Integer entidadFederativaPrepa;
    private Integer anioEgreso;
    private Double promedioGeneral;
    private String calleNo;
    private Integer entidadFederativa;
    private String municipio;
    private String codigoPostal;
    private String coloniaAspirante;
    private String correoElectronico;
    private String telefono;
    private String capacidadDiferente;
    private Character tieneBeca; // S = sí - N = no
    private String quienOtorgo;
    private String zonaProcedencia;
    private String especifiqueZonaProcedencia;
    private String programaGubernamental;

    // DATOS PADRE DEL ASPIRANTE
    private String apellidoPaternoPadre;
    private String apellidoMaternoPadre;
    private String nombrePadreAspirante;
    private Character vivePadre; //S = sí, N = no

    // DATOS MADRE DEL ASPIRANTE
    private String apellidoPaternoMadre;
    private String apellidoMaternoMadre;
    private String nombreMadreAspirante;
    private Character viveMadre; //S = sí, N = no

    // OTROS DATOS
    private String especifiqueExtrangero;

    // Para mostrar:
    private Date fechaRegistro;
}
