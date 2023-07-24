package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "materias_carreras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriasCarreras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_carrera")
    private Integer idMateriaCarrera;
    @Column(name = "carrera", length = 50)
    private String carrera;
    @Column(name = "creditos_materia")
    private Integer creditosMateria;
    @Column(name = "horas_teoricas")
    private Integer horasTeoricas;
    @Column(name = "horas_practicas")
    private Integer horasPracticas;
    @Column(name = "orden_certificado")
    private Integer ordenCertificado;
    @Column(name = "semestre_reticula")
    private Integer semestreReticula;
    @Column(name = "creditos_prerequisito")
    private Integer creditosPrerequisito;
    @Column(name = "especialidad", length = 50)
    private String especialidad;
    @Column(name = "clave_oficial_materia", length = 50)
    private String claveOficialMateria;
    @Column(name = "estatus_materia_carrera")
    private Character estatusMateriaCarrera;
    @Column(name = "programa_estudios", length = 50)
    private String programaEstudios;
    @Column(name = "renglon")
    private Integer renglon;
    @JoinColumn(name = "reticula", referencedColumnName = "reticula")
    @ManyToOne
    private Carrera reticula;
    @JoinColumn(name = "materia", referencedColumnName = "materia")
    @ManyToOne
    private Materia materia;
}
