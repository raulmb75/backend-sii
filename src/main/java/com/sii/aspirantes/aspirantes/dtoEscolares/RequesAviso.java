package com.sii.aspirantes.aspirantes.dtoEscolares;

import java.util.Date;

public class RequesAviso {


  private Long id;
  private String noControl;
  private Date vigencia;
  private String mensaje;

  public String getNoControl() {
    return noControl;
  }

  public void setNoControl(String noControl) {
    this.noControl = noControl;
  }

  public Date getVigencia() {
    return vigencia;
  }

  public void setVigencia(Date vigencia) {
    this.vigencia = vigencia;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}


