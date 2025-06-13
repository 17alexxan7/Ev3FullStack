package com.edutechEV3.soporteService.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketSoporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String estado; // Ej: "pendiente", "resuelto", etc.
    private LocalDateTime fechaCreacion;
    private Long usuarioId; // para enlazar con usuarioService
}

