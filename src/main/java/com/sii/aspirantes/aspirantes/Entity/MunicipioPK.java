package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "clave_entidad")
    private Integer claveEntidad;
    @Basic(optional = false)
    @Column(name = "clave_municipio")
    private Integer claveMunicipio;
}
