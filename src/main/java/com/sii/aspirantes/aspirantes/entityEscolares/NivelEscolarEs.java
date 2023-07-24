package com.sii.aspirantes.aspirantes.entityEscolares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "nivel_escolar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelEscolarEs {

  @Id
  @Basic(optional = false)
  @Column(name = "nivel_escolar")
  private Character nivelEscolar;
  @Column(name = "descripcion_nivel", length = 30)
  private String descripcionNivel;

}
