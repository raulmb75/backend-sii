package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class FichasCarrerasPrepaTotalDTO {

    private String nombreCarrera;
    private List<ConteoFichasPrepaDTO> listaPreparatorias;
}
