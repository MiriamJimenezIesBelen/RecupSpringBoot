package com.examenjpa.recuptema4.controller;

import com.examenjpa.recuptema4.domain.Curso;
import com.examenjpa.recuptema4.dto.CursoDTO;
import com.examenjpa.recuptema4.dto.InscripcionDTO;
import com.examenjpa.recuptema4.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/examen")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;


    @GetMapping("/cursos")
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/cursos/{id}")
    public Curso findById(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    @PostMapping("/cursos")
    public Curso save(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @PutMapping("/cursos/{id}")
    public Curso update(@PathVariable Long id,
                        @RequestBody Curso curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/cursos/{id}")
    public void delete(@PathVariable Long id) {
        cursoService.delete(id);
    }

    //  LISTADO

    @GetMapping("/cursos/listado")
    public Page<CursoDTO> listado(


            // Si no viene, por defecto cadena vacía
            @RequestParam(defaultValue = "")
            String busqueda,

            @RequestParam(defaultValue = "0")
            int pagina,

            @RequestParam(defaultValue = "5")
            int tamano,

            @RequestParam(defaultValue = "precio,asc")
            String ordenacion

    ) {


        // Separamos "precio,desc" en ["precio", "desc"]
        String[] partesOrden = ordenacion.split(",");

        // Campos que se permiten usar para ordenar.

        Set<String> camposValidos = Set.of("titulo", "resumen", "precio", "horas");

        // Empezamos con los valores por defecto
        String campo   = "precio";
        String sentido = "asc";

        if (partesOrden.length == 2) {

            //  Si el campo que llega es valido
            if (camposValidos.contains(partesOrden[0].toLowerCase())) {
                campo = partesOrden[0].toLowerCase();
            }


            if (partesOrden[1].equalsIgnoreCase("desc")) {
                sentido = "desc";
            }

        }


        // Ordenacion
        Sort sort = sentido.equals("desc")
                ? Sort.by(campo).descending()
                : Sort.by(campo).ascending();

        // Construimos el Pageable con página, tamaño y orden
        Pageable pageable = PageRequest.of(pagina, tamano, sort);


        String[] partesBusqueda = busqueda.split(",", 2);

        String campoBusqueda;
        String valorBusqueda;

        if (partesBusqueda.length == 2) {
            // Llega con campo y valor
            // Ejemplo campo = titulo
            campoBusqueda = partesBusqueda[0].trim();
            // Ejemplo valor = java
            valorBusqueda = partesBusqueda[1].trim();
        } else {
            // Si solo llega un valor sin campo, buscamos por título por defecto
            campoBusqueda = "titulo";
            valorBusqueda = busqueda.trim();
        }

        // Llamamos al servicio
        return cursoService.buscar(campoBusqueda, valorBusqueda, pageable);
    }

    // INSCRIPCIÓN

    @PostMapping("/inscribir/{alumnoId}/{cursoId}")
    public InscripcionDTO inscribir(
            @PathVariable Long alumnoId,
            @PathVariable Long cursoId
    ) {
        return cursoService.inscribir(alumnoId, cursoId);
    }
}