package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Avisos {

    @Id
    @SequenceGenerator(
            name = "aviso_sequence",
            sequenceName = "aviso_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aviso_sequence"
    )
    private Long id;
    @Column(name = "titulo", length = 80, unique = true)
    private String titulo;
    @Column(length = 2048)
    private String contenido;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne(optional = false)
    private Acceso usuario;
    @Column(name = "seccion", length = 100)
    private String seccion;
}
