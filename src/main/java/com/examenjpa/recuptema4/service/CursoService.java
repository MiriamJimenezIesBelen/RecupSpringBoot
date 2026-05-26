package com.examenjpa.recuptema4.service;

import com.examenjpa.recuptema4.domain.Curso;
import com.examenjpa.recuptema4.dto.CursoDTO;
import com.examenjpa.recuptema4.repository.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // BUSQUEDA
    public Page<CursoDTO> buscar(
            String campo,
            String valor,
            Pageable pageable
    ) {

        Page<Curso> cursos;

        switch (campo.toLowerCase()) {

            case "resumen":
                cursos = cursoRepository
                        .findByResumenContainingIgnoreCase(valor, pageable);
                break;

            case "precio":
                cursos = cursoRepository
                        .findByPrecio(
                                Double.parseDouble(valor),
                                pageable
                        );
                break;

            default:
                cursos = cursoRepository
                        .findByTituloContainingIgnoreCase(valor, pageable);
        }

        return cursos.map(c ->
                new CursoDTO(
                        c.getTitulo(),
                        c.getResumen(),
                        c.getPrecio()
                )
        );
    }

    // CRUD

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
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
}