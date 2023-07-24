package com.sii.aspirantes.aspirantes.dtoEscolares;

public class RequesEstudiante {


  private String periodoIngresoIT;

  private Integer reticula;

  private Long consecutivoInicial;

  private String tipoIngreso;

  private String añoPreInscripcion;

  public String getAñoPreInscripcion() {
    return añoPreInscripcion;
  }

  public void setAñoPreInscripcion(String añoPreInscripcion) {
    this.añoPreInscripcion = añoPreInscripcion;
  }

  public String getPeriodoIngresoIT() {
    return periodoIngresoIT;
  }

  public void setPeriodoIngresoIT(String periodoIngresoIT) {
    this.periodoIngresoIT = periodoIngresoIT;
  }

  public Integer getReticula() {
    return reticula;
  }

  public void setReticula(Integer reticula) {
    this.reticula = reticula;
  }

  public Long getConsecutivoInicial() {
    return consecutivoInicial;
  }

  public void setConsecutivoInicial(Long consecutivoInicial) {
    this.consecutivoInicial = consecutivoInicial;
  }

  public String getTipoIngreso() {
    return tipoIngreso;
  }

  public void setTipoIngreso(String tipoIngreso) {
    this.tipoIngreso = tipoIngreso;
  }


  public static class EstatusAlumno{
    private String estatusAlumno;

    public String getEstatusAlumno() {
      return estatusAlumno;
    }

    public void setEstatusAlumno(String estatusAlumno) {
      this.estatusAlumno = estatusAlumno;
    }
  }
}
