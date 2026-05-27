package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String resumen;

    private BigDecimal precio;

    private Integer horas;

    @ManyToOne
    // Crea fk nivel_id
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @OneToMany(mappedBy = "curso")
    private List<Inscripcion> inscripciones = new ArrayList<>();

}