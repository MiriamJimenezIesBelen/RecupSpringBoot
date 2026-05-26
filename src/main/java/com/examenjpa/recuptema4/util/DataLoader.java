package com.examenjpa.recuptema4.util;

import com.examenjpa.recuptema4.domain.Alumno;
import com.examenjpa.recuptema4.domain.Curso;
import com.examenjpa.recuptema4.domain.Nivel;
import com.examenjpa.recuptema4.repository.AlumnoRepository;
import com.examenjpa.recuptema4.repository.CursoRepository;
import com.examenjpa.recuptema4.repository.NivelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final NivelRepository nivelRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        log.info("Cargando datos de prueba...");

        alumnoRepository.deleteAll();
        cursoRepository.deleteAll();
        nivelRepository.deleteAll();



        // Niveles

        Nivel nivel1 = Nivel.builder().nombre("Básico").descripcion("Nivel inicial").build();
        Nivel nivel2 = Nivel.builder().nombre("Intermedio").descripcion("Nivel medio").build();
        Nivel nivel3 = Nivel.builder().nombre("Avanzado").descripcion("Nivel experto").build();

        nivelRepository.save(nivel1);
        nivelRepository.save(nivel2);
        nivelRepository.save(nivel3);

        // Alumnos

        Alumno alumno1 = Alumno.builder()
                .email("josejr@gmail.com")
                .username("Joserd")
                .password("1234")
                .build();

        alumnoRepository.save(alumno1);

        Alumno alumno2 = Alumno.builder()
                .email("sofiajisa@gmail.com")
                .username("Sofiajisa")
                .password("1234")
                .build();
        alumnoRepository.save(alumno2);

        Alumno alumno3 = Alumno.builder()
                .email("pepito@gmail.com")
                .username("pepito")
                .password("1234")
                .build();
        alumnoRepository.save(alumno3);

        Alumno alumno4 = Alumno.builder()
                .email("macarena12@gmail.com")
                .username("Macarena")
                .password("1234")
                .build();

        alumnoRepository.save(alumno4);

        Alumno alumno5 = Alumno.builder()
                .email("juan2308@gmail.com")
                .username("Juanka")
                .password("1234")
                .build();
        alumnoRepository.save(alumno5);

        // Cursos

        Curso curso1 = Curso.builder()
                .titulo("TIC")
                .resumen("ordenadores y aplicaciones")
                .precio(BigDecimal.valueOf(45.99))
                .horas(5)
                .nivel(nivel1)
                .build();

        cursoRepository.save(curso1);

        Curso curso2 = Curso.builder()
                .titulo("Francés")
                .resumen("idiomas")
                .precio(BigDecimal.valueOf(13.50))
                .horas(4)
                .nivel(nivel2)
                .build();

        cursoRepository.save(curso2);
        Curso curso3 = Curso.builder()
                .titulo("Inglés")
                .resumen("aprendemos a desarrollar el habla en inglés")
                .precio(BigDecimal.valueOf(25.99))
                .horas(8)
                .nivel(nivel3)
                .build();

        cursoRepository.save(curso3);
        Curso curso4 = Curso.builder()
                .titulo("IPE")
                .resumen("Aprenderemos las cosas básicas sobre las empresas")
                .precio(BigDecimal.valueOf(45.99))
                .horas(5)
                .nivel(nivel1)
                .build();

        cursoRepository.save(curso4);
        Curso curso5 = Curso.builder()
                .titulo("Historia")
                .resumen("conoce de donde vienes")
                .precio(BigDecimal.valueOf(23.8))
                .horas(4)
                .nivel(nivel3)
                .build();
        cursoRepository.save(curso5);






    }
}
