package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "entidades_federativas")
@AllArgsConstructor
@NoArgsConstructor
public class EntidadFederativa {

    @Id
    @Basic(optional = false)
    @Column(name = "entidad_federativa")
    private Integer entidadFederativa;
    @Basic(optional = false)
    @Column(name = "clave_entidad")
    private String claveEntidad;
    @Basic(optional = false)
    @Column(name = "nombre_entidad")
    private String nombreEntidad;
}
