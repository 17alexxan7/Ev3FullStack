package com.edutechEV3.cursoService.service;

import com.edutechEV3.cursoService.model.Curso;
import com.edutechEV3.cursoService.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCursoById_CursoExiste() {
        Curso cursoMock = new Curso();
        cursoMock.setId(1L);
        cursoMock.setNombre("Matemáticas");
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoMock));

        Curso resultado = cursoService.findCursoById(1L);
        assertEquals("Matemáticas", resultado.getNombre());
    }

    @Test
    void testFindCursoById_CursoNoExiste() {
        when(cursoRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> cursoService.findCursoById(999L));
    }

    @Test
    void testCreateCurso() {
        Curso curso = new Curso();
        curso.setNombre("Física");
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso resultado = cursoService.createCurso(curso);
        assertEquals("Física", resultado.getNombre());
    }

    @Test
    void testUpdateCurso() {
        Curso cursoExistente = new Curso();
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoExistente));
        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Curso cursoActualizado = cursoService.updateCurso(1L, new Curso());
        assertEquals("Física", cursoActualizado.getNombre());
    }

    @Test
    void testDeleteCurso() {
        Curso curso = new Curso();
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        assertDoesNotThrow(() -> cursoService.deleteCurso(1L));
        verify(cursoRepository, times(1)).delete(curso);
    }
}
