package com.edutechEV3.notificacionesService.controller;

import com.edutechEV3.notificacionesService.model.Notificacion;
import com.edutechEV3.notificacionesService.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    @PostMapping
    public Notificacion crear(@RequestBody Notificacion notificacion) {
        notificacion.setFecha(LocalDateTime.now());
        return notificacionRepository.save(notificacion);
    }

    @GetMapping("/{id}")
    public Optional<Notificacion> buscarPorId(@PathVariable Long id) {
        return notificacionRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Notificacion actualizar(@PathVariable Long id, @RequestBody Notificacion nueva) {
        return notificacionRepository.findById(id).map(n -> {
            n.setMensaje(nueva.getMensaje());
            n.setUsuarioId(nueva.getUsuarioId());
            return notificacionRepository.save(n);
        }).orElseGet(() -> {
            nueva.setId(id);
            return notificacionRepository.save(nueva);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        notificacionRepository.deleteById(id);
    }
}


