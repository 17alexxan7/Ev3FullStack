package com.edutechEV3.usuarioService.Controller;

import com.edutechEV3.usuarioService.model.Usuario;
import com.edutechEV3.usuarioService.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "API de Usuarios", description = "Endpoints para gestión de usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public EntityModel<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class).obtenerUsuarioPorId(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).obtenerUsuarios()).withRel("todos-los-usuarios"));
    }

    @Operation(summary = "Crear nuevo usuario")
    @PostMapping
    public EntityModel<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return EntityModel.of(savedUsuario,
            linkTo(methodOn(UsuarioController.class).obtenerUsuarioPorId(savedUsuario.getId())).withSelfRel());
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public EntityModel<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuarioActualizado.getNombre());
            u.setCorreo(usuarioActualizado.getCorreo());
            u.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(u);
        }).orElseGet(() -> {
            usuarioActualizado.setId(id);
            return usuarioRepository.save(usuarioActualizado);
        });
        
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class).obtenerUsuarioPorId(id)).withSelfRel());
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
