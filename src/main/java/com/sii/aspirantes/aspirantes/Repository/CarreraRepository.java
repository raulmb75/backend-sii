package com.sii.aspirantes.aspirantes.Repository;

import com.sii.aspirantes.aspirantes.Entity.Carrera;
import com.sii.aspirantes.aspirantes.dto.evaluacion.ListaCarrerasEvaluanDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    //Buscar por id (retícula) generado automáticamente

    //Bucar por nivel escolar
    @Query(value = "FROM Carrera WHERE nivelEscolar = ?1")
    List<Carrera> buscarCarreraPorNivelEscolar(Character nivelEscolar);

    //Buscar por nombre de carrera
    @Query(value = "FROM Carrera WHERE nombreCarrera = ?1")
    List<Carrera> buscarCarrerasPorNombre(String carrera);
    @Query(value = "SELECT * FROM carrera WHERE trim(nombre_carrera) = ?1 LIMIT 1",
            nativeQuery = true)
    Optional<Carrera> buscarCarreraPorNombre(String nombreCarrera);

    //Buscar por créditos totales
    @Query(value = "FROM Carrera WHERE creditosTotales = ?1")
    List<Carrera> buscarCarreraPorCreditos(int creditos);

    //Lista desplegable para las materias que evalúan
    @Query(value = "SELECT c.reticula, c.nombre_carrera AS nombreCarrera " +
            "FROM carrera c", nativeQuery = true)
    List<ListaCarrerasEvaluanDTO> buscarCarreras();
}
