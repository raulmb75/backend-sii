package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "materias_no_evaluadas",
        uniqueConstraints = @UniqueConstraint(columnNames = "grupo"))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MateriasNoEvaluadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupos grupo;
    @Column(name = "evaluacion")
    private Character evaluacion;
    @Column(name = "descripcion_de_omision", length = 200)
    private String descripcionOmision;
}
