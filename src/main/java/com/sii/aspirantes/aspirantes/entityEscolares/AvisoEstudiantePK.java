package com.sii.aspirantes.aspirantes.entityEscolares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aviso_estudiante_intermedio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisoEstudiantePK {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //@Column(name = "id_estudiante")
  @JoinColumn(name = "id_estudiante", referencedColumnName = "no_de_control")
  @ManyToOne
  private EstudianteEs idEstudiante;

  //@Column(name = "id_aviso")
  @JoinColumn(name = "id_aviso", referencedColumnName = "id")
  @ManyToOne
  private AvisoEstudianteEs idAviso;

}

