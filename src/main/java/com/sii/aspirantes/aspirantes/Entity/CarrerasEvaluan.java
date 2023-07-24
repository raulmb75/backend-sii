package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carreras_evaluan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrerasEvaluan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "carrera", referencedColumnName = "reticula")
    @ManyToOne(optional = false)
    private Carrera carrera;
    private Character evaluacion;
}
