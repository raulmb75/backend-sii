package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "asistencia_aula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaAula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asistenciaaula")
    private Integer idAsistenciaaula;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "checada")
    @Temporal(TemporalType.TIME)
    private Date checada;
    @Column(name = "observaciones", length = 100)
    private String observaciones;
    @Column(name = "observacion_da", length = 100)
    private String observacionDa;
    @Column(name = "observacion_rh", length = 100)
    private String observacionRh;
    @JoinColumn(name = "id_estatusc", referencedColumnName = "id_estatusc")
    @ManyToOne
    private EstatusChecada idEstatusc;
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horarios idHorario;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne
    private Acceso usuario;
}
