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
public class Estatus {

    @Id
    @Basic(optional = false)
    @Column(name = "id_estatus", length = 3)
    private String idEstatus;
    @Column(name = "descripcion_estatus", length = 30)
    private String descripcionEstatus;
    @OneToMany(mappedBy = "idEstatus")
    private Collection<Tarjeta> tarjetaCollection;
}
