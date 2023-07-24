package com.sii.aspirantes.aspirantes.entityEscolares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "periodo_escolar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoEscolarEs {

  @Id
  @Basic(optional = false)
  @Column(name = "periodo")
  private String periodo;
  @Basic(optional = false)
  @Column(name = "identificacion_larga", length = 50)
  private String identificacionLarga;
  @Column(name = "identificacion_corta", length = 20)
  private String identificacionCorta;
  @Basic(optional = false)
  @Column(name = "status")
  private Character status;
  @Column(name = "fecha_inicio")
  @Temporal(TemporalType.DATE)
  private Date fechaInicio;
  @Column(name = "fecha_termino")
  @Temporal(TemporalType.DATE)
  private Date fechaTermino;
  @Column(name = "inicio_vacacional_ss")
  @Temporal(TemporalType.DATE)
  private Date inicioVacacionalSs;
  @Column(name = "termino_vacacional_ss")
  @Temporal(TemporalType.DATE)
  private Date terminoVacacionalSs;
  @Column(name = "inicio_especial")
  @Temporal(TemporalType.DATE)
  private Date inicioEspecial;
  @Column(name = "fin_especial")
  @Temporal(TemporalType.DATE)
  private Date finEspecial;
  @Column(name = "cierre_horarios")
  private Character cierreHorarios;
  @Column(name = "cierre_seleccion")
  private Character cierreSeleccion;
  @Column(name = "inicio_enc_estudiantil")
  @Temporal(TemporalType.DATE)
  private Date inicioEncEstudiantil;
  @Column(name = "fin_enc_estudiantil")
  @Temporal(TemporalType.DATE)
  private Date finEncEstudiantil;
  @Column(name = "inicio_sele_alumnos")
  @Temporal(TemporalType.DATE)
  private Date inicioSeleAlumnos;
  @Column(name = "fin_sele_alumnos")
  @Temporal(TemporalType.DATE)
  private Date finSeleAlumnos;
  @Column(name = "inicio_vacacional")
  @Temporal(TemporalType.DATE)
  private Date inicioVacacional;
  @Column(name = "termino_vacacional")
  @Temporal(TemporalType.DATE)
  private Date terminoVacacional;
  @Column(name = "parcial1_inicio")
  @Temporal(TemporalType.DATE)
  private Date parcial1Inicio;
  @Column(name = "parcial1_fin")
  @Temporal(TemporalType.DATE)
  private Date parcial1Fin;
  @Column(name = "parcial2_inicio")
  @Temporal(TemporalType.DATE)
  private Date parcial2Inicio;
  @Column(name = "parcial2_fin")
  @Temporal(TemporalType.DATE)
  private Date parcial2Fin;
  @Column(name = "parcial3_inicio")
  @Temporal(TemporalType.DATE)
  private Date parcial3Inicio;
  @Column(name = "parcial3_fin")
  @Temporal(TemporalType.DATE)
  private Date parcial3Fin;
  @Column(name = "filtro")
  private Character filtro;
  @Column(name = "inicio_cal_docentes")
  @Temporal(TemporalType.DATE)
  private Date inicioCalDocentes;
  @Column(name = "fin_cal_docentes")
  @Temporal(TemporalType.DATE)
  private Date finCalDocentes;
}
