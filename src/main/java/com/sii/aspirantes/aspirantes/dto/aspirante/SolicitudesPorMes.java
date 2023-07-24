package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudesPorMes implements Serializable {

    private List<Long> noSolicitudes;
    private List<Long> noFichas;
}
