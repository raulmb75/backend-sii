package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.SeleccionMaterias;
import com.sii.aspirantes.aspirantes.Entity.SeleccionMateriasPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeleccionMateriasRepository extends JpaRepository<SeleccionMaterias, SeleccionMateriasPK> {

    //La búsqueda por id es automática (periodo, no. de control, materia, grupo)

    //Buscar por periodo
    @Query(value = "FROM SeleccionMaterias s " +
            "WHERE s.seleccionMateriasPK.periodo = ?1")
    List<SeleccionMaterias> buscarSeleccionMateriasPorPeriodo(String periodo);

    //Buscar por no. de control
    @Query(value = "FROM SeleccionMaterias s " +
            "WHERE s.seleccionMateriasPK.noDeControl = ?1")
    List<SeleccionMaterias> buscarSeleccionMateriasPorNoControl(String noControl);

    //Buscar por materia
    @Query(value = "FROM SeleccionMaterias s " +
            "WHERE s.seleccionMateriasPK.materia = ?1")
    List<SeleccionMaterias> buscarSeleccionMateriasPorMateria(String materia);

    //Buscar por materia y grupo
    @Query(value = "FROM SeleccionMaterias s " +
            "WHERE s.seleccionMateriasPK.materia = ?1 " +
            "AND s.seleccionMateriasPK.grupo = ?2")
    List<SeleccionMaterias> buscarSeleccionMateriasPorMateriaGrupo(String materia, String grupo);

    //Buscar por tipo de evaluación
    @Query(value = "FROM SeleccionMaterias WHERE tipoEvaluacion = ?1")
    List<SeleccionMaterias> buscarSeleccionMateriasPorTipoEvaluacion(String tipoEvaluacion);

    //Buscar entre rango de calificaciones
    @Query(value = "FROM SeleccionMaterias WHERE calificacion BETWEEN ?1 AND ?2 ")
    List<SeleccionMaterias> buscarSeleccionMateriasPorCalificacion(Double calificacion1, Double calificacion2);

    //Buscar por tipo de evaluación
    @Query(value = "FROM SeleccionMaterias WHERE statusSeleccion = ?1")
    List<SeleccionMaterias> buscarSeleccionMateriasPorStatusSeleccion(Character statusSeleccion);
}
