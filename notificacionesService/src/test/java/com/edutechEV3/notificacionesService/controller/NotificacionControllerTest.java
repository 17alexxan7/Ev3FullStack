package com.edutechEV3.notificacionesService.controller;

import com.edutechEV3.notificacionesService.model.Notificacion;
import com.edutechEV3.notificacionesService.repository.NotificacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)
class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionRepository notificacionRepository;

    @Test
    void testListarNotificaciones() throws Exception {
        Notificacion notificacion = new Notificacion(1L, 1L, "Mensaje", LocalDateTime.now());
        when(notificacionRepository.findAll()).thenReturn(Arrays.asList(notificacion));

        mockMvc.perform(get("/notificaciones"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].mensaje").value("Mensaje"));
    }

    @Test
    void testBuscarPorId() throws Exception {
        Notificacion notificacion = new Notificacion(1L, 1L, "Mensaje", LocalDateTime.now());
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));

        mockMvc.perform(get("/notificaciones/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.mensaje").value("Mensaje"));
    }
}

