package com.edutechEV3.notificacionesService.controller;

import com.edutechEV3.notificacionesService.model.Notificacion;
import com.edutechEV3.notificacionesService.repository.NotificacionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "API de Notificaciones", description = "Endpoints para gestión de notificaciones")
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionRepository notificacionRepository;

    public NotificacionController(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Operation(summary = "Listar todas las notificaciones")
    @GetMapping
    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    @Operation(summary = "Crear una notificación")
    @PostMapping
    public Notificacion crear(@RequestBody Notificacion notificacion) {
        notificacion.setFecha(LocalDateTime.now());
        return notificacionRepository.save(notificacion);
    }

    @Operation(summary = "Obtener una notificación por ID")
    @GetMapping("/{id}")
    public EntityModel<Notificacion> buscarPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        EntityModel<Notificacion> model = EntityModel.of(notificacion);
        model.add(linkTo(methodOn(NotificacionController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(NotificacionController.class).listar()).withRel("todas-las-notificaciones"));
        return model;
    }

    @Operation(summary = "Actualizar una notificación")
    @PutMapping("/{id}")
    public Notificacion actualizar(@PathVariable Long id, @RequestBody Notificacion nueva) {
        return notificacionRepository.findById(id).map(existente -> {
            existente.setMensaje(nueva.getMensaje());
            existente.setUsuarioId(nueva.getUsuarioId());
            return notificacionRepository.save(existente);
        }).orElseGet(() -> {
            nueva.setId(id);
            return notificacionRepository.save(nueva);
        });
    }

    @Operation(summary = "Eliminar una notificación")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        notificacionRepository.deleteById(id);
    }
}
