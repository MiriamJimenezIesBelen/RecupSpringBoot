package com.examenjpa.recuptema4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class InscripcionDTO {

    private String username;

    private String email;

    private List<String> cursos;

    private BigDecimal total_inversion;

}