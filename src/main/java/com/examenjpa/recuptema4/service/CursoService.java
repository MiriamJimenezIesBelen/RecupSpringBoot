package com.examenjpa.recuptema4.service;

import com.examenjpa.recuptema4.domain.Alumno;
import com.examenjpa.recuptema4.domain.Curso;
import com.examenjpa.recuptema4.domain.Inscripcion;
import com.examenjpa.recuptema4.dto.CursoDTO;
import com.examenjpa.recuptema4.dto.InscripcionDTO;
import com.examenjpa.recuptema4.repository.AlumnoRepository;
import com.examenjpa.recuptema4.repository.CursoRepository;
import com.examenjpa.recuptema4.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoRepository alumnoRepository;
    private final InscripcionRepository inscripcionRepository;


    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElseThrow();
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso curso) {
        Curso c = cursoRepository.findById(id).orElseThrow();
        c.setTitulo(curso.getTitulo());
        c.setResumen(curso.getResumen());
        c.setPrecio(curso.getPrecio());
        c.setHoras(curso.getHoras());
        c.setNivel(curso.getNivel());
        return cursoRepository.save(c);
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }


    public Page<CursoDTO> buscar(String campoBusqueda,
                                 String valorBusqueda,
                                 Pageable pageable) {
        Page<Curso> cursos;

        // Si el valor está vacío , devuelve todos paginados
        if (valorBusqueda == null || valorBusqueda.isEmpty()) {

            cursos = cursoRepository.findAll(pageable);

        } else {

            // Según el campo que llegó, buscamos por ese campo de busqueda
            switch (campoBusqueda.toLowerCase()) {

                case "resumen":
                    cursos = cursoRepository
                            .findByResumenContainingIgnoreCase(valorBusqueda, pageable);
                    break;

                case "titulo":
                default:
                    cursos = cursoRepository
                            .findByTituloContainingIgnoreCase(valorBusqueda, pageable);
                    break;
            }
        }

        // Transforma cada Curso en CursoDTO (solo titulo, resumen, precio)
        return cursos.map(c -> new CursoDTO(
                c.getTitulo(),
                c.getResumen(),
                c.getPrecio()
        ));
    }

    // INSCRIPCIÓN

    public InscripcionDTO inscribir(Long alumnoId, Long cursoId) {

        // Busca alumno y curso, lanza excepción automática si no existen
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow();
        Curso curso   = cursoRepository.findById(cursoId).orElseThrow();

        // Crea y guarda la inscripción en la tabla intermedia
        Inscripcion inscripcion = Inscripcion.builder()
                .alumno(alumno)
                .curso(curso)
                .build();

        inscripcionRepository.save(inscripcion);

        // Recargamos las inscripciones del alumno para incluir la recién guardada
        Alumno alumnoActualizado = alumnoRepository.findById(alumnoId).orElseThrow();

        // Saca el título de cada curso inscrito
        List<String> cursos = alumnoActualizado.getInscripciones()
                .stream()
                .map(i -> i.getCurso().getTitulo())
                .toList();

        // Suma los precios de todos los cursos
        BigDecimal total = alumnoActualizado.getInscripciones()
                .stream()
                .map(i -> i.getCurso().getPrecio())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new InscripcionDTO(
                alumno.getUsername(),
                alumno.getEmail(),
                cursos,
                total
        );
    }
}