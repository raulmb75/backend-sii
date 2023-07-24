package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "estatus_checada")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstatusChecada {
    @Id
    @Basic(optional = false)
    @Column(name = "id_estatusc", length = 3)
    private String idEstatusc;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idEstatusc")
    private Collection<AsistenciaAula> asistenciaAulaCollection;
}
