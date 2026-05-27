package com.examenjpa.recuptema4.repository;

import com.examenjpa.recuptema4.domain.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Page<Curso> findByResumenContainingIgnoreCase(String resumen, Pageable pageable);
}