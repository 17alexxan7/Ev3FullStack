package com.edutechEV3.cursoService.controller;

import com.edutechEV3.cursoService.model.Curso;
import com.edutechEV3.cursoService.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "API de Cursos", description = "Endpoints para gestión de cursos")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Obtener todos los cursos")
    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    @Operation(summary = "Obtener un curso por ID")
    @GetMapping("/{id}")
    public EntityModel<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findCursoById(id);
        EntityModel<Curso> model = EntityModel.of(curso);
        model.add(linkTo(methodOn(CursoController.class).getCursoById(id)).withSelfRel());
        model.add(linkTo(methodOn(CursoController.class).getAllCursos()).withRel("todos-los-cursos"));
        return model;
    }

    @Operation(summary = "Crear un nuevo curso")
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }

    @Operation(summary = "Actualizar un curso existente")
    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        return cursoService.updateCurso(id, cursoDetails);
    }

    @Operation(summary = "Eliminar un curso")
    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }
}


