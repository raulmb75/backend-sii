package com.sii.aspirantes.aspirantes.entityEscolares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "grupos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GruposEs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_grupo")
  private Integer idGrupo;
  @Column(name = "estatus_grupo")
  private Character estatusGrupo;
  @Column(name = "capacidad_grupo")
  private Integer capacidadGrupo;
  @Column(name = "alumnos_inscritos")
  private Integer alumnosInscritos;
  @Column(name = "folio_acta", length = 20)
  private String folioActa;
  @Column(name = "paralelo_de", length = 30)
  private String paraleloDe;
  @Column(name = "carrera", length = 50)
  private String carrera;
  @Column(name = "seleccionado_en_bloque")
  private Character seleccionadoEnBloque;
  @Column(name = "tipo_personal")
  private Character tipoPersonal;
  @Column(name = "materia", length = 50)
  private String materia;
  @Column(name = "grupo", length = 30)
  private String grupo;
  @JoinColumn(name = "reticula", referencedColumnName = "reticula")
  @ManyToOne
  private CarreraEs reticula;
  @JoinColumn(name = "id_materia_carrera", referencedColumnName = "id_materia_carrera")
  @ManyToOne
  private MateriasCarrerasEs idMateriaCarrera;
  @JoinColumn(name = "periodo", referencedColumnName = "periodo")
  @ManyToOne
  private PeriodoEscolarEs periodo;
  @JoinColumn(name = "rfc", referencedColumnName = "rfc")
  @ManyToOne
  private PersonalEs rfc;
}
