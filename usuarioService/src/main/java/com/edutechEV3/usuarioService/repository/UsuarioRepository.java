package com.edutechEV3.usuarioService.repository;

import com.edutechEV3.usuarioService.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

