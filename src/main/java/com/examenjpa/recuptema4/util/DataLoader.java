package com.examenjpa.recuptema4.util;

import com.examenjpa.recuptema4.domain.*;
import com.examenjpa.recuptema4.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AlumnoRepository alumnoRepository;

    private final CursoRepository cursoRepository;

    private final NivelRepository nivelRepository;

    private final InscripcionRepository inscripcionRepository;


    @Override
    public void run(String... args) throws Exception {

        Nivel basico = nivelRepository.save(
                Nivel.builder()
                        .nombre("Basico")
                        .descripcion("Nivel basico")
                        .build()
        );

        Nivel intermedio = nivelRepository.save(
                Nivel.builder()
                        .nombre("Intermedio")
                        .descripcion("Nivel intermedio")
                        .build()
        );

        Nivel avanzado = nivelRepository.save(
                Nivel.builder()
                        .nombre("Avanzado")
                        .descripcion("Nivel avanzado")
                        .build()
        );

        Curso c1 = cursoRepository.save(
                Curso.builder()
                        .titulo("Java")
                        .resumen("Curso Java")
                        .precio(BigDecimal.valueOf(50))
                        .horas(40)
                        .nivel(basico)
                        .build()
        );

        Curso c2 = cursoRepository.save(
                Curso.builder()
                        .titulo("Spring")
                        .resumen("Curso Spring")
                        .precio(BigDecimal.valueOf(80))
                        .horas(60)
                        .nivel(intermedio)
                        .build()
        );

        Curso c3 = cursoRepository.save(
                Curso.builder()
                        .titulo("React")
                        .resumen("Curso React")
                        .precio(BigDecimal.valueOf(90))
                        .horas(50)
                        .nivel(avanzado)
                        .build()
        );

        Curso c4 = cursoRepository.save(
                Curso.builder()
                        .titulo("SQL")
                        .resumen("Curso SQL")
                        .precio(BigDecimal.valueOf(40))
                        .horas(30)
                        .nivel(basico)
                        .build()
        );

        Curso c5 = cursoRepository.save(
                Curso.builder()
                        .titulo("Docker")
                        .resumen("Curso Docker")
                        .precio(BigDecimal.valueOf(100))
                        .horas(70)
                        .nivel(avanzado)
                        .build()
        );

        Alumno a1 = alumnoRepository.save(
                Alumno.builder()
                        .email("juan@gmail.com")
                        .username("juan")
                        .password("1234")
                        .build()
        );

        Alumno a2 = alumnoRepository.save(
                Alumno.builder()
                        .email("ana@gmail.com")
                        .username("ana")
                        .password("1234")
                        .build()
        );

        alumnoRepository.save(
                Alumno.builder()
                        .email("pepe@gmail.com")
                        .username("pepe")
                        .password("1234")
                        .build()
        );

        alumnoRepository.save(
                Alumno.builder()
                        .email("lucia@gmail.com")
                        .username("lucia")
                        .password("1234")
                        .build()
        );

        alumnoRepository.save(
                Alumno.builder()
                        .email("maria@gmail.com")
                        .username("maria")
                        .password("1234")
                        .build()
        );

        inscripcionRepository.save(
                Inscripcion.builder()
                        .alumno(a1)
                        .curso(c1)
                        .build()
        );

        inscripcionRepository.save(
                Inscripcion.builder()
                        .alumno(a1)
                        .curso(c2)
                        .build()
        );

        inscripcionRepository.save(
                Inscripcion.builder()
                        .alumno(a2)
                        .curso(c3)
                        .build()
        );
    }
}