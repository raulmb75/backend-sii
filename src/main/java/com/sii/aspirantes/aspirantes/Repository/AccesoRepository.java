package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Acceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, String> {

    @Query(value = "FROM Acceso WHERE LOWER(usuario) = LOWER(?1)")
    public Acceso buscarUsuario(String usuario);

}
