package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public interface FichasCarrerasPrepaDTO {
    String getNombreCarrera();
    Integer getPreparatoria1();
    Integer getPreparatoria2();
    Integer getPreparatoria3();
    Integer getPreparatoria4();
    Integer getPreparatoria5();
    Integer getOtras();
}

