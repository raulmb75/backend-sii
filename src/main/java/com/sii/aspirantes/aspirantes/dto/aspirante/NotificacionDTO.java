package com.sii.aspirantes.aspirantes.dto.aspirante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {

    private String asunto;
    private String contenido;
}
