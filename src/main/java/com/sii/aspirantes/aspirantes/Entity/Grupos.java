package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grupos {

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
    @Column(name = "grupo", length = 3)
    private String grupo;
    @JoinColumn(name = "reticula", referencedColumnName = "reticula")
    @ManyToOne
    private Carrera reticula;
    @JoinColumn(name = "id_materia_carrera", referencedColumnName = "id_materia_carrera")
    @ManyToOne
    private MateriasCarreras idMateriaCarrera;
    @JoinColumn(name = "periodo", referencedColumnName = "periodo")
    @ManyToOne
    private PeriodoEscolar periodo;
    @JoinColumn(name = "rfc", referencedColumnName = "rfc")
    @ManyToOne
    private Personal rfc;
}
