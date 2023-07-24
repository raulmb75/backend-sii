package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Data;

//Request para los datos de registro del aspirante
@Data
@AllArgsConstructor
public class DatosRegistroAspiranteDTO {
    private String nombreAspirante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer nip;
    private String curp;
    private String correoElectronico;
}
