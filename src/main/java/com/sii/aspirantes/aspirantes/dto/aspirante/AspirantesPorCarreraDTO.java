package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estadística de fichas de aspirantes por carrera
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AspirantesPorCarreraDTO {
    private String carrera;
    private Long noFichas;
}
