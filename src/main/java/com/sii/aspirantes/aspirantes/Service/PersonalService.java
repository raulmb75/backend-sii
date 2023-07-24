package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Entity.Personal;
import com.sii.aspirantes.aspirantes.Repository.PersonalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository){
        this.personalRepository = personalRepository;
    }

    //Buscar todos
    public List<Personal> buscarTodos(){
        return personalRepository.findAll();
    }

    //Buscar por ID
    public Personal buscarPersonalPorRfc(String rfc){
        Personal personal =  personalRepository.findById(rfc)
                .orElseThrow(()->
                        new UsernameNotFoundException("Personal con rfc " + rfc + " no registrado"));

        return personal;
    }

    //Insertar datos / Actualizar datos
    public Personal guardarDatosEmpleado(Personal datosEmpleado){
        return personalRepository.save(datosEmpleado);
    }

    public Personal editarDatosEmpleado(String rfc, Personal datosPersonal){
        // Método no testeado
        Personal personalRegistrado = personalRepository.findById(rfc).orElse(null);

        if(personalRegistrado != null){
            return personalRepository.save(datosPersonal);
        }

        throw new EntityNotFoundException("Personal con RFC " + rfc + " no encontrado");
    }

    //Eliminar
    public void eliminarEmpleado(String rfc){
        personalRepository.deleteById(rfc);
    }

    //Buscar por clave de área (departamento)
    public List<Personal> buscarPersonalPorClaveArea(String claveArea){
        return personalRepository.buscarPersonalPorClaveArea(claveArea);
    }
    //Buscar por lugar de nacimiento
    public List<Personal> buscarPersonalPorLugarNacimiento(Integer lugarNacimiento){
        return personalRepository.buscarPersonalPorLugarNacimiento(lugarNacimiento);
    }
    //Buscar por grado máximo de estudios
    public List<Personal> buscarPersonalPorGradoMaxEstudios(String gradoMaxEstudios){
        return personalRepository.buscarPersonalPorGradoMaxEstudios(gradoMaxEstudios);
    }
    //Buscar por sexo
    public List<Personal> buscarPersonalPorSexo(Character sexo){
        return personalRepository.buscarPersonalPorSexo(sexo);
    }
    //Buscar por localidad
    public List<Personal> buscarPersonalPorLocalidad(String localidad){
        return personalRepository.buscarPersonalPorLocalidad(localidad);
    }
    //Buscar por status del empleado
    public List<Personal> buscarPersonalPorStatusEmpleado(String statusEmpleado){
        return personalRepository.buscarPersonalPorStatusEmpleado(statusEmpleado);
    }
}
