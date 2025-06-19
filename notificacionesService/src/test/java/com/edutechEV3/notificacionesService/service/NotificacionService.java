package com.edutechEV3.notificacionesService.service;

import com.edutechEV3.notificacionesService.model.Notificacion;
import com.edutechEV3.notificacionesService.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public Notificacion crear(Notificacion notificacion) {
        notificacion.setFecha(LocalDateTime.now());
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> buscarPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion actualizar(Long id, Notificacion nueva) {
        return notificacionRepository.findById(id).map(existente -> {
            existente.setMensaje(nueva.getMensaje());
            existente.setUsuarioId(nueva.getUsuarioId());
            return notificacionRepository.save(existente);
        }).orElseGet(() -> {
            nueva.setId(id);
            return notificacionRepository.save(nueva);
        });
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}
