package com.edutechEV3.soporteService.controller;

import com.edutechEV3.soporteService.model.TicketSoporte;
import com.edutechEV3.soporteService.repository.TicketSoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soporte")
public class TicketSoporteController {

    @Autowired
    private TicketSoporteRepository repo;

    @GetMapping
    public List<TicketSoporte> listar() {
        return repo.findAll();
    }

    @PostMapping
    public TicketSoporte crear(@RequestBody TicketSoporte ticket) {
        ticket.setFechaCreacion(java.time.LocalDateTime.now());
        ticket.setEstado("pendiente");
        return repo.save(ticket);
    }

    @GetMapping("/{id}")
    public Optional<TicketSoporte> buscarPorId(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public TicketSoporte actualizar(@PathVariable Long id, @RequestBody TicketSoporte nuevo) {
        return repo.findById(id).map(t -> {
            t.setTitulo(nuevo.getTitulo());
            t.setDescripcion(nuevo.getDescripcion());
            t.setEstado(nuevo.getEstado());
            return repo.save(t);
        }).orElseGet(() -> {
            nuevo.setId(id);
            return repo.save(nuevo);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

