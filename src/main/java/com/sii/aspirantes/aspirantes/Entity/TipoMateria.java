package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipo_materia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoMateria {
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_materia")
    private Integer tipoMateria;
    @Column(name = "nombre_tipo", length = 50)
    private String nombreTipo;
//    @OneToMany(mappedBy = "tipoMateria")
//    private Collection<Materia> materiaCollection;
}
