package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor {

    @Id
    @Basic(optional = false)
    @Column(name = "rfc", length = 13)
    private String rfc;
    @Column(length = 512)
    private String password;
    @Column(length = 50)
    private String nombre;
    @Column(name = "apellido_paterno", length = 50)
    private String apellidoPaterno;
    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;
    @Column(length = 15)
    private String telefono;
    @Column(name = "genero")
    private Character genero;
    @Column(name = "correo_eectronico", length = 60, unique = true)
    private String correoElectronico;
    @Column(length = 150)
    private String domicilio;
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private Date fechaRegistro;
}
