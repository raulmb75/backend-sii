package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cursos {

    @Id
    @Basic(optional = false)
    @Column(length = 50)
    private String clave;
    @Column(name = "nombre_curso", length = 200)
    private String nombreCurso;
    @JoinColumn(name = "clave_area", referencedColumnName = "clave_area")
    @ManyToOne(optional = false)
    private Organigrama claveArea;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "horas")
    private int horas;
    @Column(name = "horario", length = 50)
    private String horario;
    @Column(name = "cupo")
    private int cupo;
    @Column(length = 100)
    private String lugar;
    @JoinColumn(name = "periodo", referencedColumnName = "periodo")
    @ManyToOne
    private PeriodoEscolar periodo;
    @Column(name = "fecha_registro_curso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroCurso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cursos_instructores",
            joinColumns = @JoinColumn(name = "clave"),
            inverseJoinColumns = @JoinColumn(name = "rfc"))
    private Set<Instructor> instructores = new HashSet<>();
}
