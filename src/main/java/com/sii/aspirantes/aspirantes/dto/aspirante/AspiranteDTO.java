package com.sii.aspirantes.aspirantes.dto.aspirante;

import java.util.Date;

/**
 * Lista de solicitudes de aspirantes registrados
 * Lista de solicitudes de aspirantes
 *
 * Utilizado también para el registro histórico de solicitudes y fichas
 */
public interface AspiranteDTO {
    Long getNoSolicitud();
    String getApellidoPaterno();
    String getApellidoMaterno();
    String getNombreAspirante();
    Integer getNip();
    String getCurp();
    String getCorreoElectronico();
    String getTelefono();
    Date getFechaNacimiento();
    Character getGenero();
}
