package com.sii.aspirantes.aspirantes.Entity;

import com.sii.aspirantes.aspirantes.dto.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @Basic(optional = false)
    @Column(name = "idrol")
    private String idrol;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private ERole rol;

    public Roles(ERole rol){
        this.rol = rol;
    }
    /*@OneToMany(mappedBy = "idrol")
    private Collection<Tarjeta> tarjetaCollection;
    @OneToMany(mappedBy = "idrol")
    private Collection<Usuario> usuarioCollection;*/
}
