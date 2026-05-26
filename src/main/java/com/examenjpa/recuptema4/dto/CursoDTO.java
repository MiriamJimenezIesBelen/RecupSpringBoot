package com.examenjpa.recuptema4.dto;

import com.examenjpa.recuptema4.domain.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO que expone únicamente título, resumen y precio de un Curso.
 * Ejercicio 3 – Filtros, Paginación y Ordenación.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoDTO {

    private String titulo;
    private String resumen;
    private BigDecimal precio;

    /** Convierte una entidad Curso en este DTO. */
    public static CursoDTO from(Curso curso) {
        return CursoDTO.builder()
                .titulo(curso.getTitulo())
                .resumen(curso.getResumen())
                .precio(curso.getPrecio())
                .build();
    }
}