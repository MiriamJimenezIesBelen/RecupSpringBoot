package com.examenjpa.recuptema4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CursoDTO {

    private String titulo;

    private String resumen;

    private BigDecimal precio;

}