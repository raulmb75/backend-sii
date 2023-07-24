package com.sii.aspirantes.aspirantes.entityEscolares;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aviso_estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisoEstudianteEs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "fecha_caducidad")
  private Date vigencia;

  @Column(name = "mensaje")
  private String mensaje;
}
