package com.sii.aspirantes.aspirantes.dtoEscolares;

import java.util.Date;

public class RequesAspirante {
  public static class Curp{
    private String curp;

    public String getCurp() {
      return curp;
    }

    public void setCurp(String curp) {
      this.curp = curp;
    }
  }

  public static class NuevoAspirante{
    private Long noSolicitud;
    private int periodo;
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int nip;
    private Date fechaNacimiento;
    private String estadoCivil;
    private char genero; // H - M
    private String nacionalidad;
    private String curp;
    private String carreraOp1;
    private String carreraOp2;
    private String clavePreparatoria;
    private String entidadFederativaPrepa;
    private int anioEgreso;
    private double promedioGeneral;
    private String calleNo;
    private String entidadFederativa; //Revisar tipo de dato
    private String municipio; //Revisar tipo de dato
    private String codigoPostal;
    private String coloniaAspirante;
    private String correoElectronico;
    private String telefono;
    private String capacidadDiferente;
    private char tieneBeca; // S = sí - N = no
    private String quienOtorgo;
    private String zonaProcedencia;
    private String especifiqueZonaProcedencia;
    private String programaGubernamental;

    // DATOS PADRE DEL ASPIRANTE
    private String apellidoPaternoPadre;
    private String apellidoMaternoPadre;
    private String nombrePadreAspirante;
    private char vivePadre; //S = sí, N = no

    // DATOS MADRE DEL ASPIRANTE
    private String apellidoPaternoMadre;
    private String apellidoMaternoMadre;
    private String nombreMadreAspirante;
    private char viveMadre; //S = sí, N = no

    private String fechaAtencion; //23-02-2023
    private String horaAtencion; // 23:05:23
    private String itmin; // ¿Qué es? Puede permanecer nulo
    private int folioCeneval;
    private Date fechaPago;
    private Long noRecibo;
    private String instituto;
    private String especifiqueExtrangero;
    private Date fechaRegistro;
  }
}
