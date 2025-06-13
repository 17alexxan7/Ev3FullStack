package com.edutechEV3.cursoService.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}



