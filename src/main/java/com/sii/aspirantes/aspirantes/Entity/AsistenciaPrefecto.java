package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaPrefecto {
    @Id
    @Basic(optional = false)
    @Column(name = "id_asistencia_prefecto")
    private Integer idAsistenciaPrefecto;
    @Column(name = "usuario", length = 30)
    private String usuario;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "observaciones", length = 200)
    private String observaciones;
    @Column(name = "motivo_falta", length = 50)
    private String motivoFalta;
    @Column(name = "asistencia")
    private Character asistencia;
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horarios idHorario;
}
