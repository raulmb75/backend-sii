package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.OneToMany;

import java.util.Collection;

@Entity
@Table(name = "nivel_escolar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelEscolar {

    @Id
    @Basic(optional = false)
    @Column(name = "nivel_escolar")
    private Character nivelEscolar;
    @Column(name = "descripcion_nivel", length = 30)
    private String descripcionNivel;
//    @OneToMany(mappedBy = "nivelEscolar")
//    private Collection<Materia> materiaCollection;
}
