package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ComparativoSolicitudesDTO implements Serializable {
    private Integer numero;
    private Integer subtotal;
    private String rangoFechas;
}
