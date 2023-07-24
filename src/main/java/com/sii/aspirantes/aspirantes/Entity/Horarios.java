package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario")
    private Integer idHorario;
    @Basic(optional = false)
    @Column(name = "tipo_horario")
    private Character tipoHorario;
    @Basic(optional = false)
    @Column(name = "dia_semana")
    private Short diaSemana;
    @Column(name = "hora_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicial;
    @Column(name = "hora_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinal;
    @Column(name = "grupo", length = 3)
    private String grupo;
    @Column(name = "aula", length = 6)
    private String aula;
    @Column(name = "actividad", length = 10)
    private String actividad;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicio;
    @Column(name = "vigencia_fin")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFin;
    @Basic(optional = false)
    @Column(name = "consecutivo_admvo")
    private Integer consecutivoAdmvo;
    @Column(name = "tipo_personal")
    private Character tipoPersonal;
    @OneToMany(mappedBy = "idHorario", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<AsistenciaPrefecto> asistenciaPrefectoCollection;
    @OneToMany(mappedBy = "idHorario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AsistenciaAula> asistenciaAulaCollection;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupos idGrupo;
    @JoinColumn(name = "materia", referencedColumnName = "materia")
    @ManyToOne
    private Materia materia;
    @JoinColumn(name = "periodo", referencedColumnName = "periodo")
    @ManyToOne(optional = false)
    private PeriodoEscolar periodo;
    @JoinColumn(name = "rfc", referencedColumnName = "rfc")
    @ManyToOne
    private Personal rfc;
}
