package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Table(name = "estatus_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstatusUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestatus")
    private Integer idestatus;
    @Basic(optional = false)
    @Column(name = "estatus")
    private String estatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestatus")
    private Collection<Acceso> usuarioCollection;
}
