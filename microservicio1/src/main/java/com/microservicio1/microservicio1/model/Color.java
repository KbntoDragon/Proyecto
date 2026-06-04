package com.microservicio1.microservicio1.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colores")
public class Color {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Este campo No debe ir vacio")
    @Column(nullable = false, unique = true)    
    private String nombre;
}
