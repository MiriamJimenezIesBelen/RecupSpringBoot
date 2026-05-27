package com.examenjpa.recuptema4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "alumno")
    private List<Inscripcion> inscripciones = new ArrayList<>();

}