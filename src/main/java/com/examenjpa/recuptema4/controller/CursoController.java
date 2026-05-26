package com.examenjpa.recuptema4.controller;

import com.examenjpa.recuptema4.domain.Curso;
import com.examenjpa.recuptema4.dto.CursoDTO;
import com.examenjpa.recuptema4.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // LISTADO + FILTROS + PAGINACION
    @GetMapping
    public ResponseEntity<Page<CursoDTO>> findAll(
            @RequestParam(required = false) String busqueda,
            Pageable pageable
    ) {

        String campo = "titulo";
        String valor = "";

        if (busqueda != null) {

            String[] partes = busqueda.split(",");

            if (partes.length == 2) {
                campo = partes[0];
                valor = partes[1];
            } else {
                valor = busqueda;
            }
        }

        return ResponseEntity.ok(
                cursoService.buscar(campo, valor, pageable)
        );
    }

    // GET ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {

        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoService.save(curso));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(
            @PathVariable Long id,
            @RequestBody Curso curso
    ) {

        return ResponseEntity.ok(
                cursoService.update(id, curso)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        cursoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}