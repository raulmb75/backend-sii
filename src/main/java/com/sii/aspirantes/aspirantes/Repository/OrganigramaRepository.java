package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Organigrama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrganigramaRepository extends JpaRepository<Organigrama, String> {

    //Buscar por id (claveArea) es generado automáticamente

    //Buscar descripción del área
    @Query(value = "FROM Organigrama WHERE descripcionArea = ?1")
    Organigrama buscarAreaPorDescripcion(String descripcion);

    //Buscar por nivel
    @Query(value = "FROM Organigrama WHERE nivel = ?1")
    List<Organigrama> buscarAreaPorNivel(Integer nivel);

    //Buscar por tipo de área
    @Query(value = "FROM Organigrama WHERE tipoArea = ?1")
    List<Organigrama> buscarAreaPorTipo(Character tipoArea);

    //Buscar por dependencia (de área)
    @Query(value = "FROM Organigrama WHERE areaDepende =?1")
    List<Organigrama> buscarAreaPorDependencia(String areaDepende);
}
