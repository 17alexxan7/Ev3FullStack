package com.edutechEV3.soporteService.controller;

import com.edutechEV3.soporteService.model.TicketSoporte;
import com.edutechEV3.soporteService.repository.TicketSoporteRepository;
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

@WebMvcTest(TicketSoporteController.class)
class TicketSoporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketSoporteRepository ticketSoporteRepository;

    @Test
    void testListarTickets() throws Exception {
        TicketSoporte ticket = new TicketSoporte(1L, "Error", "Descripción", "pendiente", LocalDateTime.now(), 1L);
        when(ticketSoporteRepository.findAll()).thenReturn(Arrays.asList(ticket));

        mockMvc.perform(get("/soporte"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].titulo").value("Error"));
    }

    @Test
    void testBuscarPorId() throws Exception {
        TicketSoporte ticket = new TicketSoporte(1L, "Error", "Descripción", "pendiente", LocalDateTime.now(), 1L);
        when(ticketSoporteRepository.findById(1L)).thenReturn(Optional.of(ticket));

        mockMvc.perform(get("/soporte/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.titulo").value("Error"));
    }
}
