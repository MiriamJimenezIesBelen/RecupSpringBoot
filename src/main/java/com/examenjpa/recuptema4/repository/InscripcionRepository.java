package com.examenjpa.recuptema4.repository;

import com.examenjpa.recuptema4.domain.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository
        extends JpaRepository<Inscripcion, Long> {
}