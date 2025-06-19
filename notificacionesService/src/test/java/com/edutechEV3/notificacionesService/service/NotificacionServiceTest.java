package com.edutechEV3.notificacionesService.service;

import com.edutechEV3.notificacionesService.model.Notificacion;
import com.edutechEV3.notificacionesService.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearNotificacion() {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Nueva notificación");
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);

        Notificacion resultado = notificacionService.crear(notificacion);
        assertEquals("Nueva notificación", resultado.getMensaje());
    }

    @Test
    void testBuscarPorId_NotificacionExiste() {
        Notificacion notificacion = new Notificacion(1L, 1L, "Mensaje", LocalDateTime.now());
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));

        Notificacion resultado = notificacionService.buscarPorId(1L).orElse(null);
        assertNotNull(resultado);
        assertEquals("Mensaje", resultado.getMensaje());
    }

    @Test
    void testBuscarPorId_NotificacionNoExiste() {
        when(notificacionRepository.findById(999L)).thenReturn(Optional.empty());
        assertFalse(notificacionService.buscarPorId(999L).isPresent());
    }
}


