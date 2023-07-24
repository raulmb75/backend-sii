package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeleccionMateriasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @Column(name = "no_de_control")
    private String noDeControl;
    @Basic(optional = false)
    @Column(name = "materia")
    private String materia;
    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;
}
