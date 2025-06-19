package com.edutechEV3.usuarioService.controller;

import com.edutechEV3.usuarioService.Controller.UsuarioController;
import com.edutechEV3.usuarioService.model.Usuario;
import com.edutechEV3.usuarioService.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario(1L, "Test", "test@test.com", "USER");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombre").value("Test"));
    }
}
