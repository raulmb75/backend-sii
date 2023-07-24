package com.sii.aspirantes.aspirantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InstructorDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
