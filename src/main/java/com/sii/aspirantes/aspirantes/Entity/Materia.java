package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {

    @Id
    @Basic(optional = false)
    @Column(name = "materia", length = 15)
    private String materia;
    @Basic(optional = false)
    @Column(name = "nombre_completo_materia", length = 100)
    private String nombreCompletoMateria;
    @Basic(optional = false)
    @Column(name = "nombre_abreviado_materia", length = 30)
    private String nombreAbreviadoMateria;
    @JoinColumn(name = "nivel_escolar", referencedColumnName = "nivel_escolar")
    @ManyToOne
    private NivelEscolar nivelEscolar;
    @JoinColumn(name = "clave_area", referencedColumnName = "clave_area")
    @ManyToOne
    private Organigrama claveArea;
    @JoinColumn(name = "tipo_materia", referencedColumnName = "tipo_materia")
    @ManyToOne
    private TipoMateria tipoMateria;
}
