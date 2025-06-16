package com.edutechEV3.cursoService.service;

import com.edutechEV3.cursoService.model.Curso;
import com.edutechEV3.cursoService.repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso findCursoById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso cursoDetails) {
        Curso curso = findCursoById(id);
        curso.setNombre(cursoDetails.getNombre());
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Long id) {
        Curso curso = findCursoById(id);
        cursoRepository.delete(curso);
    }
}


