package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lista de fichas de aspirantes por carrera
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AspiranteFichaCarerraDTO {

    private Long noFicha;
    private Long noSolicitud;
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Character sexo;
    private String escuelaProcedencia;
    private String correoElectronico;
    private String telefono;
    private String curp;
    private String carrera;

    // Podrían agregarse más datos, según la necesidad de D.A.
}
