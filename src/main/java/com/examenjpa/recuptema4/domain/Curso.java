package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String titulo;

    @Column(length = 255)
    private String resumen;

    @Column(precision = 38, scale = 2)
    private BigDecimal precio;

    private int horas;

    @ManyToOne
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @ManyToMany(mappedBy = "cursos")
    @Builder.Default
    private List<Alumno> alumnos = new ArrayList<>();
}
