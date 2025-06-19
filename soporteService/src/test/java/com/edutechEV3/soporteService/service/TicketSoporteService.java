package com.edutechEV3.soporteService.service;

import com.edutechEV3.soporteService.model.TicketSoporte;
import com.edutechEV3.soporteService.repository.TicketSoporteRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketSoporteService {

    private final TicketSoporteRepository ticketSoporteRepository;

    public TicketSoporteService(TicketSoporteRepository ticketSoporteRepository) {
        this.ticketSoporteRepository = ticketSoporteRepository;
    }

    public List<TicketSoporte> listar() {
        return ticketSoporteRepository.findAll();
    }

    public TicketSoporte crear(TicketSoporte ticket) {
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado("pendiente");
        return ticketSoporteRepository.save(ticket);
    }

    public Optional<TicketSoporte> buscarPorId(Long id) {
        return ticketSoporteRepository.findById(id);
    }

    public TicketSoporte actualizar(Long id, TicketSoporte nuevo) {
        return ticketSoporteRepository.findById(id).map(existente -> {
            existente.setTitulo(nuevo.getTitulo());
            existente.setDescripcion(nuevo.getDescripcion());
            existente.setEstado(nuevo.getEstado());
            return ticketSoporteRepository.save(existente);
        }).orElseGet(() -> {
            nuevo.setId(id);
            return ticketSoporteRepository.save(nuevo);
        });
    }

    public void eliminar(Long id) {
        ticketSoporteRepository.deleteById(id);
    }
}
