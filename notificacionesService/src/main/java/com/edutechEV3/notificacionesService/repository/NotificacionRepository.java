package com.edutechEV3.notificacionesService.repository;

import com.edutechEV3.notificacionesService.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}

