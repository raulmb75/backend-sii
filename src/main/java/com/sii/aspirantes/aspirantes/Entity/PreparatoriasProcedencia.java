package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "preparatorias_de_procedencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparatoriasProcedencia {

    @Id
    @Basic(optional = false)
    @Column(name = "clave_preparatoria", nullable = false, length = 10)
    private String clavePreparatoria;
    @Column(name = "nombre_preparatoria", length = 100)
    private String nombrePreparatoria;
    @Column(name = "entidad_federativa")
    private Integer entidadFederativa;
    @Column(length = 50)
    private String municipio;
    @Column(length = 50)
    private String colonia;
    @Column(length = 100)
    private String servicio;
    @Column(length = 50)
    private String sostenimiento;
}
