package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")

    @EqualsAndHashCode.Include
    private Long id;


    @Column(length = 120)
    private String email;

    @Column(length = 120)
    private String username;

    @Column(length = 255)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "inscripcion",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )

    @Builder.Default
    private List<Curso> cursos = new ArrayList<>();

}
