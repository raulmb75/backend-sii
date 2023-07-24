package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Personal;
import com.sii.aspirantes.aspirantes.dto.evaluacion.DocentesEvaluacionAlumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, String> {
    //Insertar datos
    //Buscar por ID
    //Buscar todos
    //Eliminar
    //Actualizar

    //Buscar por área
    @Query(value = "FROM Personal p WHERE p.claveArea.claveArea = ?1")
    List<Personal> buscarPersonalPorClaveArea(String claveArea);
    //Buscar por lugar de nacimiento
    @Query(value = "FROM Personal WHERE lugarNacimiento = ?1")
    List<Personal> buscarPersonalPorLugarNacimiento(Integer lugarNacimiento);
    //Buscar por grado máximo de estudios
    @Query(value = "FROM Personal WHERE gradoMaximoEstudios = ?1")
    List<Personal> buscarPersonalPorGradoMaxEstudios(String gradoMaxEstudios);
    //Buscar por sexo
    @Query(value = "FROM Personal WHERE sexoEmpleado = ?1")
    List<Personal> buscarPersonalPorSexo(Character sexo);
    //Buscar por localidad
    @Query(value = "FROM Personal WHERE localidad = ?1")
    List<Personal> buscarPersonalPorLocalidad(String localidad);
    //Buscar por status del empleado
    @Query(value = "FROM Personal WHERE estatusEmpleado = ?1")
    List<Personal> buscarPersonalPorStatusEmpleado(String statusEmpleado);
    


   
}
