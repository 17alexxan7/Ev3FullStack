package com.edutechEV3.cursoService.repository;
import com.edutechEV3.cursoService.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}

