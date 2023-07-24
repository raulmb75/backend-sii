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
public class Tarjeta {
    @Id
    @Basic(optional = false)
    @Column(name = "id_tarjeta",length = 18)
    private String idTarjeta;
    @Column(name = "usuario",length = 13)
    private String usuario;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne
    private Estatus idEstatus;
    @JoinColumn(name = "id_rol", referencedColumnName = "idrol")
    @ManyToOne
    private Roles idRol;
}
