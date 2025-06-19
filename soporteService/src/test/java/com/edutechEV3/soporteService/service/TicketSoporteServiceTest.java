package com.edutechEV3.soporteService.service;

import com.edutechEV3.soporteService.model.TicketSoporte;
import com.edutechEV3.soporteService.repository.TicketSoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketSoporteServiceTest {

    @Mock
    private TicketSoporteRepository ticketSoporteRepository;

    @InjectMocks
    private TicketSoporteService ticketSoporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearTicket() {
        TicketSoporte ticket = new TicketSoporte();
        ticket.setTitulo("Error en login");
        when(ticketSoporteRepository.save(any(TicketSoporte.class))).thenReturn(ticket);

        TicketSoporte resultado = ticketSoporteService.crear(ticket);
        assertEquals("Error en login", resultado.getTitulo());
    }

    @Test
    void testBuscarPorId_TicketExiste() {
        TicketSoporte ticket = new TicketSoporte(1L, "Error", "No puedo iniciar sesión", "pendiente", LocalDateTime.now(), 1L);
        when(ticketSoporteRepository.findById(1L)).thenReturn(Optional.of(ticket));

        Optional<TicketSoporte> resultado = ticketSoporteService.buscarPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Error", resultado.get().getTitulo());
    }
}
