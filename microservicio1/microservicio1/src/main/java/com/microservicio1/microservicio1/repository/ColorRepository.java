package com.microservicio1.microservicio1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio1.microservicio1.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Optional<Color> findByNombre(String nombre);
}
