package com.edutechEV3.soporteService.controller;

import com.edutechEV3.soporteService.model.TicketSoporte;
import com.edutechEV3.soporteService.repository.TicketSoporteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "API de Soporte", description = "Endpoints para gestión de tickets de soporte")
@RestController
@RequestMapping("/soporte")
public class TicketSoporteController {

    private final TicketSoporteRepository ticketSoporteRepository;

    public TicketSoporteController(TicketSoporteRepository ticketSoporteRepository) {
        this.ticketSoporteRepository = ticketSoporteRepository;
    }

    @Operation(summary = "Listar todos los tickets")
    @GetMapping
    public List<TicketSoporte> listar() {
        return ticketSoporteRepository.findAll();
    }

    @Operation(summary = "Crear un nuevo ticket")
    @PostMapping
    public EntityModel<TicketSoporte> crear(@RequestBody TicketSoporte ticket) {
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado("pendiente");
        TicketSoporte savedTicket = ticketSoporteRepository.save(ticket);
        
        EntityModel<TicketSoporte> model = EntityModel.of(savedTicket);
        model.add(linkTo(methodOn(TicketSoporteController.class).buscarPorId(savedTicket.getId())).withSelfRel());
        model.add(linkTo(methodOn(TicketSoporteController.class).listar()).withRel("todos-los-tickets"));
        return model;
    }

    @Operation(summary = "Obtener un ticket por ID")
    @GetMapping("/{id}")
    public EntityModel<TicketSoporte> buscarPorId(@PathVariable Long id) {
        TicketSoporte ticket = ticketSoporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        EntityModel<TicketSoporte> model = EntityModel.of(ticket);
        model.add(linkTo(methodOn(TicketSoporteController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(TicketSoporteController.class).listar()).withRel("todos-los-tickets"));
        return model;
    }

    @Operation(summary = "Actualizar un ticket")
    @PutMapping("/{id}")
    public EntityModel<TicketSoporte> actualizar(@PathVariable Long id, @RequestBody TicketSoporte nuevo) {
        TicketSoporte updatedTicket = ticketSoporteRepository.findById(id).map(existente -> {
            existente.setTitulo(nuevo.getTitulo());
            existente.setDescripcion(nuevo.getDescripcion());
            existente.setEstado(nuevo.getEstado());
            return ticketSoporteRepository.save(existente);
        }).orElseGet(() -> {
            nuevo.setId(id);
            return ticketSoporteRepository.save(nuevo);
        });

        EntityModel<TicketSoporte> model = EntityModel.of(updatedTicket);
        model.add(linkTo(methodOn(TicketSoporteController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(TicketSoporteController.class).listar()).withRel("todos-los-tickets"));
        return model;
    }

    @Operation(summary = "Eliminar un ticket")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ticketSoporteRepository.deleteById(id);
    }
}
