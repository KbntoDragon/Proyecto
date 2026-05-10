package com.tallerBici.proyecto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Optional<Color> findByNombre(String nombre);
}
