package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "municipios")
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @EmbeddedId
    protected MunicipioPK municipioPK;
    @Column(name = "nombre_municipio", nullable = false)
    private String nombreMunicipio;

}
