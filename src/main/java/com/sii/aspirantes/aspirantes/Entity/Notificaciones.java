package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String asunto;
    @Column(length = 1024)
    private String contenido;
    @Column(name = "fecha_de_emision")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date fechaCreacion;
}
