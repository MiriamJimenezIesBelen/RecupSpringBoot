package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}