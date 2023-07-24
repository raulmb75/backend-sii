package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aula_examen_admision")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AulaExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String aula;
    @Column(nullable = false)
    private Short capacidad;
    private Short disponibilidad;
    @Column(nullable = false)
    private Short asignados;
    @JoinColumn(name = "carrera", referencedColumnName = "reticula")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrera carrera;

}
