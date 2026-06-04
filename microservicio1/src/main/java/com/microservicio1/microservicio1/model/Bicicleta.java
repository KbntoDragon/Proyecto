package com.microservicio1.microservicio1.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank (message = "El material es obligatorio")
    @Size(max = 50, message = "El material debe tener un maximo de 50 caracteres")
    @Column(nullable = false, length = 50)
    private String material;
    
}
