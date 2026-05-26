package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
