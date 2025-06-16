package com.edutechEV3.cursoService.controller;

import com.edutechEV3.cursoService.model.Curso;
import com.edutechEV3.cursoService.service.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Test
    void testGetAllCursos() throws Exception {
        List<Curso> cursos = Arrays.asList(
            new Curso(),
            new Curso()
        );
        when(cursoService.getAllCursos()).thenReturn(cursos);

        mockMvc.perform(get("/api/cursos"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nombre").value("Matemáticas"))
               .andExpect(jsonPath("$[1].nombre").value("Física"));
    }

    @Test
    void testGetCursoById() throws Exception {
        Curso curso = new Curso();
        when(cursoService.findCursoById(1L)).thenReturn(curso);

        mockMvc.perform(get("/api/cursos/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombre").value("Matemáticas"));
    }
}


