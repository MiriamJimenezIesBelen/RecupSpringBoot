package com.examenjpa.recuptema4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Respuesta del endpoint de inscripción.
 * Contiene: resumen del alumno, cursos inscritos y total_inversion.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscripcionDTO {

    private String username;
    private String email;
    private List<CursoDTO> cursos;
    private BigDecimal total_inversion;
}